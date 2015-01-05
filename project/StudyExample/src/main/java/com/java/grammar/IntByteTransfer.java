package com.gw.grammar;

/**
 * desc： byte范围-128~+127
 * 1、 int转成byte 即int最低一个字节（总共占用四个字节）
 * 2、 当int大于127小于256,即最低一个字节的最高位为1，为负数  转化时为取int对应二进制补码-1  
 *    如  129-----1000 0001   补码-1为-0111 1110-1 为-127
 *    
 * @author darwen
 * 2014-4-4 上午11:08:52
 */
public class IntByteTransfer {
	
   private static final byte[] bitvalues = new byte[] {
		    (byte)0x01,
		    (byte)0x02,
		    (byte)0x04,
		    (byte)0x08,
		    (byte)0x10,
		    (byte)0x20,
		    (byte)0x40,
		    (byte)0x80
		  };
	

	public static void main(String[] args) {
     
		 for(int i = 0; i < bitvalues.length; i++){
			 System.out.println("values: " + bitvalues[i]);
		 }

		int a = 1129;
		int b = 130;
		System.out.println("a: " + ((byte)a + (byte)b));  //这里是先转化byte再进行相加
	}

}
