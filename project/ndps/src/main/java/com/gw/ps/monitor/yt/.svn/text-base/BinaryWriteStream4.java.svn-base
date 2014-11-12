package com.gw.ps.monitor.yt;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.ArrayUtils;

public class BinaryWriteStream4 {

	public ByteString m_data = null;

	public BinaryWriteStream4() {
		m_data = new ByteString(Consts.BINARY_PACKLEN_LEN_2);
	}

	// 返回不含头的内容
	public byte[] getContent() {
		byte[] data = getData();
		return ArrayUtils.subarray(data, Consts.BINARY_PACKLEN_LEN_2, data.length);
	}

	public boolean Write(String str, int length) {
		byte buf[] = new byte[5];
		// AtomicInteger buflen = new AtomicInteger(0);
		// compress_(length, buf, buflen);
		// m_data.append(buf, buflen.get());

		int compress_ = compress_(length, buf);
		m_data.append(buf, compress_);
		m_data.append(str, length);
		return true;
	}

	public boolean WriteNoCompress(String str, int length) {
		m_data.append(str, length);
		return true;
	}

	public boolean Write(int i) {
		byte[] array = FormatTransfer.toHH(i);
		m_data.append(array);
		return true;
	}

	public boolean Write(short s) {
		byte[] array = FormatTransfer.toHH(s);
		m_data.append(array);
		return true;
	}

	public boolean Write(char c) {
		m_data.append((byte) c);
		return true;
	}

	public static int compress_(int i, byte[] buf) {
		int ret = 0;
		for (int a = 4; a >= 0; a--) {
			char c;
			c = (char) (i >> (a * 7) & 0x7f);
			if (c == 0x00 && ret == 0)
				continue;

			if (a == 0)
				c &= 0x7f;
			else
				c |= 0x80;
			buf[ret] = (byte) c;
			ret++;
		}
		if (ret == 0) {
			ret++;
			buf[0] = 0;
		}
		return ret;
	}

	public static boolean compress_(int i, byte[] buf, AtomicInteger len) {
		len.set(0);
		for (int a = 4; a >= 0; a--) {
			char c;
			c = (char) (i >> (a * 7) & 0x7f);
			if (c == 0x00 && len.get() == 0)
				continue;

			if (a == 0)
				c &= 0x7f;
			else
				c |= 0x80;
			buf[len.get()] = (byte) c;
			len.set(len.get() + 1);
		}
		if (len.get() == 0) {
			len.set(len.get() + 1);
			buf[0] = 0;
		}
		return true;
	}

	public void Flush() {
		m_data.flip();

		byte[] ulen = FormatTransfer.toHH((int) m_data.length());
		m_data.put(0, ulen, ulen.length);

	}

	public long getSize() {
		return m_data.length();
	}

	public byte[] getData() {
		return m_data.getBytes();
	}

	public ByteString getBuffer() {
		return m_data;
	}

	public void clear() {
		m_data.clear();
		m_data.append(new byte[4]);
	}

	public static String bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		BinaryWriteStream4 writeStream = new BinaryWriteStream4();
		writeStream.Write((short) 13);
		writeStream.Write(0);
		writeStream.WriteNoCompress("aa", "aa".length());
		writeStream.Flush();
		byte[] data = writeStream.getData();
	}

	public static void testByteBuffer(ByteBuffer bb) throws UnsupportedEncodingException {
		String str = "中国";
		byte[] bytes = str.getBytes("ISO-8859-1");
		bb = ByteBuffer.wrap(bytes);
		System.out.println(bb.array().length);
	}

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}
}
