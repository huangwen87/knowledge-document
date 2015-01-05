package com.gw.grammar;

import java.util.PriorityQueue;

public class PriorityQueueTest {

/**
 * 默认是按照自然顺序  即数字小的   字母小的具有优先级高   先出列
 * 
 * */
	public static void main(String[] args) {
		
		PriorityQueue<String> queue = new PriorityQueue<String>();
		
		queue.add("test3");
		queue.add("test2");
		queue.add("test1");
		
		String str;
        while( (str = queue.poll()) != null ){
        	System.out.println("str: " + str);
        }
		
	}

}
