package com.java.grammar;

class JvmMain {

	public static void foo() {
		try {
			System.out.println("try");
			foo();
		} catch (Exception e) {
			System.out.println("catch");
			foo();
		} finally {
			System.out.println("finally");
			foo();
		}
	}

	public static void fooAgain() throws Exception {
		throw new Exception("fooAgain");
	}

	public static void main(String[] args) {
		foo();
	}
}