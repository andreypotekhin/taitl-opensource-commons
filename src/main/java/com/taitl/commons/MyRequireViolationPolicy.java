package com.taitl.commons;

import java.util.*;

import org.valid4j.errors.*;
import org.valid4j.impl.*;

/**
 * Throws IllegalArgumentException instead of Valid4j-specific RequireViolation.
 */
class MyRequireViolationPolicy extends RequireViolationPolicy implements ViolationPolicy
{
	/**
	 * Constructs an object of this class.
	 * 
	 * @param violations Violations queue.
	 */
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