package com.taitl.commons;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;

/**
 * Base class for our test cases. Contains some constants, redirects
 * standard output into a member string {@link #output}.
 * 
 * @author Andrey Potekhin
 *
 */
public class CommonTest extends TestCase
{
	/** Method under test must throw an exception. */
	protected static final String EXCEPTION_MUST_BE_THROWN = "Exception must be thrown.";

	/** Method under test must not throw an exception. */
	protected static final String WRONG_EXCEPTION = "Exception must not be thrown here.";

	/** Standard output captured into a string. */
	String output;

	/** A custom stream to replace standard output. */
	private ByteArrayOutputStream outputStream;

	/** Original value of standard output. */
	private PrintStream oldOut;

	// Make sure anyone using this class has the dependencies properly initialized
	static
	{
		new TaitlCommons();
	}

	/**
	 * Sets up execution of a test case, sets standard output
	 * to custom value for capturing into {@link output} member.
	 * @throws Exception as specified by Juni4
	 */
	public void setUp() throws Exception
	{
		outputStream = new ByteArrayOutputStream();
		oldOut = System.out;
		System.setOut(new PrintStream(outputStream));
	}

	/**
	 * Completes execution of test case, sets standard output
	 * back to its original value.
	 * @throws Exception as specified by Juni4
	 */
	public void tearDown() throws Exception
	{
		outputStream = null;
		System.setOut(oldOut);
	}

	/**
	 * Gets the standard output captured in the {@link output} member
	 * as effect of setUp() method.
	 * 
	 * @return The value of {@link output} member
	 */
	protected String output()
	{
		output = outputStream.toString();
		return output;
	}
}
