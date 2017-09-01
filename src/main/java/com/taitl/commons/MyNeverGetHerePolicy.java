package com.taitl.commons;

import java.util.*;

import org.valid4j.errors.*;
import org.valid4j.impl.*;

/**
 * Throws IllegalStateException instead of valid4j-specific NeverGetHereViolation.
 */
class MyNeverGetHerePolicy extends NeverGetHerePolicy implements ViolationPolicy
{
	/**
	 * Constructs an object of this class.
	 * 
	 * @param violations Violations queue.
	 */
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