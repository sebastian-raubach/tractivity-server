package uk.co.raubach.tractivity.server.pojo;

/**
 * @author Sebastian Raubach
 */
public enum ServerProperty
{
	ADMIN_USERNAME("admin.username", "admin", true),
	ADMIN_PASSWORD("admin.password", "password", true),
	DATA_DIRECTORY_EXTERNAL("data.directory.external", null, true),
	DATABASE_SERVER("database.server", null, true),
	DATABASE_NAME("database.name", null, true),
	DATABASE_USERNAME("database.username", null, true),
	DATABASE_PASSWORD("database.password", null, false),
	DATABASE_PORT("database.port", null, false),;

	String  key;
	String  defaultValue;
	boolean required;

	ServerProperty(String key, String defaultValue, boolean required)
	{
		this.key = key;
		this.defaultValue = defaultValue;
		this.required = required;
	}

	public String getKey()
	{
		return key;
	}

	public String getDefaultValue()
	{
		return defaultValue;
	}

	public boolean isRequired()
	{
		return required;
	}
}
