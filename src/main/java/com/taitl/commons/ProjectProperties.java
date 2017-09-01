package com.taitl.commons;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Loads properties from src/main/resources/project.properties file
 * in your Maven project structure and merges them into System properties.
 * 
 * @author Andrey Potekhin
 */
public class ProjectProperties
{
	/**
	 * This project's properties file.
	 */
	private static final String PROPERTIES_FILE_NAME = "/system.properties";

	static
	{
		init();
	}

	/**
	 * Protected constructor for utility class.
	 */
	protected ProjectProperties()
	{
	}

	/**
	 * Initializes this library.
	 */
	public static void init()
	{
		loadSystemProperties(ProjectProperties.class);
	}

	/**
	 * Loads properties from src/main/resources/project.properties file
	 * and merges them into System properties.
	 * 
	 * Usage:
	 * loadSystemProperties(this.getClass());
	 * 
	 * The class parameter is required to identify your project within
	 * the class path, as done by Class.getResourceAsStream() method.
	 * 
	 * @param c Any class in your project.
	 */
	@SuppressWarnings("rawtypes")
	public static void loadSystemProperties(Class c)
	{
		loadSystemProperties(c, PROPERTIES_FILE_NAME);
	}

	/**
	 * Loads properties from a file and merges them into System properties.
	 * 
	 * @param c A class as required by Class.getResourceAsStream() method.
	 * @param fileName Properties file name.
	 */
	@SuppressWarnings("rawtypes")
	public static void loadSystemProperties(Class c, String fileName)
	{
		InputStream input = c.getResourceAsStream(fileName);

		if (input == null)
		{
			throw new RuntimeException("Missing required properties file: '" + fileName + "'");
		}

		Properties properties = new Properties();
		try
		{
			properties.load(input);
		}
		catch (IOException ioe)
		{
			throw new RuntimeException(ioe);
		}

		for (Map.Entry<Object, Object> entry : properties.entrySet())
		{
			System.setProperty(entry.getKey().toString(), entry.getValue().toString());
		}
	}
}
