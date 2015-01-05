package com.memory;


public class HeapOOM {

	private static final int _1MB= 1024 * 1024;  //约1m

	public static void main(String[] args) {

		//总共约8m多，堆大小设置不超过8388608B即8.388608m就会内存溢出，但是需要整数，小于8M就会重现这个错误
        byte[] a1, a2, a3, a4;  
        a1 = new byte[2 * _1MB];  
        a2 = new byte[2 * _1MB];  
        a3 = new byte[2 * _1MB];  
        a4 = new byte[2 * _1MB];  

	}

}
