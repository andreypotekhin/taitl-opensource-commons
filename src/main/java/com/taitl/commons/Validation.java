package com.taitl.commons;

import org.hamcrest.Matcher;

/**
 * Abstracts client code from concrete validation provider
 * (Valid4j in our case).
 * 
 * @author Andrey Potekhin
 *
 */
public class Validation
{
	/**
	 * Protected constructor for an utility class.
	 */
	protected Validation()
	{
	}

	/**
	 * Precondition that clients are required to fulfill.
	 * Violations are considered to be programming errors,
	 * on the clients part.
	 *
	 * @param condition the condition to check
	 */
	public static void require(boolean condition)
	{
		org.valid4j.Assertive.require(condition);
	}

	/**
	 * Precondition that clients are required to fulfill.
	 * Violations are considered to be programming errors,
	 * on the clients part.
	 *
	 * @param <T>     type of object to check
	 * @param o       the object to check
	 * @param matcher the matcher that the given object must satisfy
	 * @return the validated object
	 */
	public static <T> T require(T o, Matcher<?> matcher)
	{
		return org.valid4j.Assertive.require(o, matcher);
	}

	/**
	 * Precondition that clients are required to fulfill.
	 * Violations are considered to be programming errors,
	 * on the clients part.
	 *
	 * @param condition the condition to check
	 * @param msg       message {@link java.lang.String#format format string}
	 * @param values    values passed into the msg format string
	 */
	public static void require(boolean condition, String msg, Object... values)
	{
		org.valid4j.Assertive.require(condition, msg, values);
	}

	/**
	 * Postcondition that supplier are supposed to ensure.
	 * Violations are considered to be a programming error,
	 * on the suppliers part.
	 *
	 * @param condition the condition to check
	 */
	public static void ensure(boolean condition)
	{
		org.valid4j.Assertive.ensure(condition);
	}

	/**
	 * Postcondition that supplier are supposed to ensure.
	 * Violations are considered to be programming errors,
	 * on the suppliers part.
	 *
	 * @param <T>     type of object to check
	 * @param o       the object to check
	 * @param matcher the matcher that the given object must satisfy
	 * @return        the validated object
	 */
	public static <T> T ensure(T o, Matcher<?> matcher)
	{
		return org.valid4j.Assertive.ensure(o, matcher);
	}

	/**
	 * Postcondition that supplier are supposed to ensure.
	 * Violations are considered to be programming errors,
	 * on the suppliers part.
	 *
	 * @param condition the condition to check
	 * @param msg       message {@link java.lang.String#format format string}
	 * @param values    values passed into the msg format string
	 */
	public static void ensure(boolean condition, String msg, Object... values)
	{
		org.valid4j.Assertive.ensure(condition, msg, values);
	}
}
