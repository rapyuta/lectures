package com.ohhoonim.test.common;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class StringTest {
	@Test
	public void substringTest() {
		String result1 = "abc".substring(0, 2);
		assertThat(result1, is("ab"));

		result1 = "abc".substring(0, 1);
		assertThat(result1, is("a"));
	}
	
	@Test
	public void valueOfTest() {
		String result = "abc".valueOf(true);
		assertThat(result, is("true"));
		
		result = "abc".valueOf(false);
		assertThat(result, is("false"));

		result = "abcddfefa".valueOf(0.0f);
		assertThat(result, is("0.0"));

		
		
		
	}
}
