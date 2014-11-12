package com.gw.ps.utils;

public class ByteUtil {
	public static long hBytesToLong(byte[] b) {
		long s = 0;
		for (int i = 0; i < 7; i++) {
			if (b[i] >= 0) {
				s = s + b[i];
			} else {
				s = s + 256 + b[i];
			}
			s = s * 256;
		}
		if (b[7] >= 0) {
			s = s + b[7];
		} else {
			s = s + 256 + b[7];
		}
		return s;
	}

	public static byte[] toLH(long n) {
		byte[] b = new byte[8];
		b[0] = (byte) (n & 0xff);
		b[1] = (byte) (n >> 8 & 0xff);
		b[2] = (byte) (n >> 16 & 0xff);
		b[3] = (byte) (n >> 24 & 0xff);
		b[4] = (byte) (n >> 32 & 0xff);
		b[5] = (byte) (n >> 40 & 0xff);
		b[6] = (byte) (n >> 48 & 0xff);
		b[7] = (byte) (n >> 56 & 0xff);
		return b;
	}

	public static long long2HL(long n) {
		return hBytesToLong(toLH(n));
	}

}
