package com.taitl.commons;

import org.valid4j.AssertiveProvider;
import org.valid4j.CheckPolicy;
import org.valid4j.UnreachablePolicy;
import org.valid4j.errors.ContractViolation;
import org.valid4j.impl.CheckingPolicy;
import org.valid4j.impl.EnsureViolationPolicy;
import org.valid4j.impl.NeverGetHerePolicy;
import org.valid4j.impl.RequireViolationPolicy;
import org.valid4j.impl.ViolationPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Provider of default assertive policies for valid4j.
 */
// @SuppressWarnings("unused")
public class Valid4jProvider implements AssertiveProvider
{
	private static final int MAX_CAPACITY = 10;
	private static Queue<ContractViolation> violations;

	/**
	 * Get the first contract violations that have occurred in current process.
	 * @return the (up to 10) first contract violations in the order of occurrence
	 */
	public static List<ContractViolation> getFirstTrackedViolations()
	{
		return new ArrayList<>(violations);
	}

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

// Throws IllegalArgumentException instead of valid4j-specific RequireViolation
class MyRequireViolationPolicy extends RequireViolationPolicy implements ViolationPolicy
{
	public MyRequireViolationPolicy(Queue<ContractViolation> violations)
	{
		super(violations);
	}

	@Override
	public void handleViolation(String message)
	{
		throw new IllegalArgumentException(message);
	}
}

// Throws IllegalStateException instead of valid4j-specific EnsureViolation
class MyEnsureViolationPolicy extends EnsureViolationPolicy implements ViolationPolicy
{
	public MyEnsureViolationPolicy(Queue<ContractViolation> violations)
	{
		super(violations);
	}

	@Override
	public void handleViolation(String message)
	{
		throw new IllegalStateException(message);
	}
}

// Throws IllegalStateException instead of valid4j-specific NeverGetHereViolation
class MyNeverGetHerePolicy extends NeverGetHerePolicy implements ViolationPolicy
{
	public MyNeverGetHerePolicy(Queue<ContractViolation> violations)
	{
		super(violations);
	}

	@Override
	public void handleViolation(String message)
	{
		throw new IllegalStateException(message);
	}
}
