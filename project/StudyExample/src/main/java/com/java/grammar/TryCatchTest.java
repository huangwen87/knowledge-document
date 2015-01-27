package com.java.grammar;

public class TryCatchTest {

    static int f1(){ 
	    try {  
	    	   System.out.println("try");
	    	   throw new Exception("try error");  
	           //return 1;  
	        }catch(Exception e){
	           //return 2;
	            throw new RuntimeException("catch error"); 
	        } 
	        finally {  
	            System.out.println("f1");  
	        }  
	}
	 
	public static void main(String[] args){
		System.out.println("��ʼ���ԡ�����");
		try {  
            System.out.println(" : " + f1());  
        } catch (Exception e) {  
            System.out.println(" a: " + e.getMessage());  
        }  
	}

}
