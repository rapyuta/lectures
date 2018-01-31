package main;

import common.Util;

public class Main {

	public static void main(String[] args) {
		
		String result = Util.emptyBlank("Test String");
		System.out.println(result);
		
		result = Util.emptyBlank(null);
		System.out.println(result);

	}

}
