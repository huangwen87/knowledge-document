package sentiment.util;

public class CommonUsing {
	public static long modify(long data){
		byte[] oldbytes = new byte[8];
		long[] newbytes = new long[8];
		long result = 0L;
		for(int i = 0; i < oldbytes.length; i++){
			oldbytes[i] = new Long(data & 0xff).byteValue();
			data = data >> 8;
		}
		for(int i = 0; i < newbytes.length; i++){
			newbytes[i] = oldbytes[7 - i] & 0xff;
			newbytes[i] <<= 8 * i;
			result = result | newbytes[i];
		}
		return result;
	}
}
