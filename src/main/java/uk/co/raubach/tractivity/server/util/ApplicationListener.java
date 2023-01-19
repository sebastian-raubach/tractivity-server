package uk.co.raubach.tractivity.server.util;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import uk.co.raubach.tractivity.server.database.*;
import uk.co.raubach.tractivity.server.pojo.ServerProperty;

import java.io.*;
import java.util.concurrent.ScheduledExecutorService;

/**
 * The {@link ApplicationListener} is the main {@link ServletContextListener} of the application. It's started when the application is loaded by
 * Tomcat. It contains {@link #contextInitialized(ServletContextEvent)} which is executed on start and {@link #contextDestroyed(ServletContextEvent)}
 * which is executed when the application terminates.
 *
 * @author Sebastian Raubach
 */
@WebListener
public class ApplicationListener implements ServletContextListener
{
	private static ScheduledExecutorService backgroundScheduler;

	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		PropertyWatcher.initialize();
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent)
	{
		PropertyWatcher.stopFileWatcher();

		Database.close();
	}

	/**
	 * Returns the file with the given name from the external data folder in the given sub directory structure
	 *
	 * @param filename The name of the file to return
	 * @param subdirs  Optional sub-directory structure
	 * @return The {@link File} representing the request
	 */
	public static File getFromExternal(String filename, String... subdirs)
		throws IOException
	{
		File folder = new File(PropertyWatcher.get(ServerProperty.DATA_DIRECTORY_EXTERNAL));

		if (subdirs != null)
		{
			for (String subdir : subdirs)
			{
				folder = new File(folder, subdir);
			}
		}

		File result = new File(folder, filename);

		if (!FileUtils.isSubDirectory(folder, result))
			throw new IOException("Invalid file access");

		return result;
	}
}
