package com.taitl.commons;

import java.util.*;
import java.util.concurrent.*;

import org.valid4j.*;
import org.valid4j.errors.*;
import org.valid4j.impl.*;

/**
 * Provider of default assertive policies for valid4j.
 */
// @SuppressWarnings("unused")
public class Valid4jProvider implements AssertiveProvider
{
	/** Initial capacity for violations queue. */
	private static final int MAX_CAPACITY = 10;

	/** Violations queue. */
	private static Queue<ContractViolation> violations;

	/**
	 * Get the first contract violations that have occurred in current process.
	 * @return the (up to 10) first contract violations in the order of occurrence
	 */
	public static List<ContractViolation> getFirstTrackedViolations()
	{
		return new ArrayList<>(violations);
	}

	/**
	 * Constructs an object of this class.
	 */
	public Valid4jProvider()
	{
		violations = new ArrayBlockingQueue<ContractViolation>(MAX_CAPACITY);
	}

	@Override
	public CheckPolicy requirePolicy()
	{
		return new CheckingPolicy(new MyRequireViolationPolicy(violations));
	}

	@Override
	public CheckPolicy ensurePolicy()
	{
		return new CheckingPolicy(new MyEnsureViolationPolicy(violations));
	}

	@Override
	public UnreachablePolicy neverGetHerePolicy()
	{
		return new MyNeverGetHerePolicy(violations);
	}
}
