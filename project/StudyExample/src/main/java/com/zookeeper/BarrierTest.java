package com.zookeeper;

import java.util.Random;

/**
 *  保证三个进程全部进入之后再删除（删除和创建的顺序不定）
 *  @author darwen
 *  2013-12-24  下午3:20:29
 */
public class BarrierTest {


	public static void main(String[] args) throws Exception{
		 for (int i = 0; i < 3; i++) {
			    Barrier barrier = new Barrier("/zookeeper/barrier", 3);
	            Process p = new Process("Thread-" + i, barrier);
	            p.start();  
	     }  

	}
}
	
	
class Process extends Thread {  
		  
	    private String  name;  
	    private Barrier barrier;  
	  
	    public Process(String name, Barrier barrier){  
	        this.name = name;  
	        this.barrier = barrier;  
	    }  
	  
	    @Override  
	    public void run() {  
	        try {  
	            barrier.enter(name);  
	            System.out.println(name + " enter");  
	            Thread.sleep(1000 + new Random().nextInt(2000));  
	            barrier.leave(name);  
	            System.out.println(name + " leave");  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }
}
