package com.maxtrain.mockito_examples;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import org.junit.Test;

public class CoreMockitoTest {

	@Test(expected = NullPointerException.class)
	public void testMockitoCore() {
		// You can mock concrete classes, not just interfaces
		LinkedList mockedList = mock(LinkedList.class);

		// stubbing
		when(mockedList.get(0)).thenReturn("first");
		when(mockedList.get(1)).thenThrow(new NullPointerException());

		// following prints "first"
		System.out.println(mockedList.get(0));

		// following throws runtime exception
		System.out.println(mockedList.get(1));

		// following prints "null" because get(999) was not stubbed
		System.out.println(mockedList.get(999));

		// Although it is possible to verify a stubbed invocation, usually it's just
		// redundant
		// If your code cares what get(0) returns, then something else breaks (often
		// even before verify() gets executed).
		// If your code doesn't care what get(0) returns, then it should not be stubbed.
		verify(mockedList).get(0);
	}
}
