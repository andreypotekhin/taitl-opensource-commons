package com.taitl.commons;


/**
 * Initializes dependency libraries, such as Valid4j.
 * 
 * @author Andrey Potekhin
 *
 */
public class TaitlCommons
{
	static
	{
		init();
	}

	/**
	 * Protected constructor for utility class.
	 */
	protected TaitlCommons()
	{
	}

	/**
	 * Initializes this library.
	 */
	public static void init()
	{
		/*
		 * Init Valid4j with our custom assertive policies provider. Our provider maps violations to
		 * IllegalArgumentException, IllegalStateException which is more in line with traditional Java parameter and
		 * object state validation.
		 */
		ProjectProperties.init();
		String v = System.getProperty("org.valid4j.AssertiveProvider");
		assert !v.isEmpty();
	}
}
