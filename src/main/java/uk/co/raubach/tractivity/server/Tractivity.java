package uk.co.raubach.tractivity.server;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api/")
public class Tractivity extends ResourceConfig
{
	public Tractivity()
	{
		packages("uk.co.raubach.tractivity.server");
		register(MultiPartFeature.class);
	}
}
