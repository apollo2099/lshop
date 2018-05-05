package com.gv.html.struts;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 实现缓存输出和及时flush页面流
 * @author caicl
 *
 */
public class HtmlResponseWriter extends PrintWriter{
	public static final Log logger = LogFactory.getLog(HtmlResponseWriter.class);
	
	private static final char[] LINE_SEP =
	        System.getProperty("line.separator").toCharArray();
	
	protected static final int DEFAULT_BUFFER_SIZE = 8*1024;
    
	protected int hasWrited = 0;
	
	protected char[] charbuff = null;
    
    
	public HtmlResponseWriter(OutputStream outputStream) throws FileNotFoundException {
		super(outputStream, true);
		charbuff = new char[DEFAULT_BUFFER_SIZE];
	}
	public HtmlResponseWriter(String fileName, String encode) throws FileNotFoundException, UnsupportedEncodingException {
		super(fileName, encode);
		charbuff = new char[DEFAULT_BUFFER_SIZE];
	}
	@Override
	public void flush() {
		if (hasWrited > 0) {
			super.write(charbuff, 0, hasWrited);
			//reset
			hasWrited = 0;
		}
		super.flush();
	}

	@Override
	public void close() {
		if (hasWrited > 0) {
			super.write(charbuff, 0, hasWrited);
			super.flush();
		}
		super.close();
	}

	@Override
	public void write(int c) {
		write(Integer.toString(c));
	}

	@Override
	public void write(char[] buf, int off, int len) {
		if (hasWrited + len >= DEFAULT_BUFFER_SIZE) {
			flush();
		}
		if (len > DEFAULT_BUFFER_SIZE) {
			//大于缓存
			int slen = len, soff = off;
			while(slen > DEFAULT_BUFFER_SIZE) {
				System.arraycopy(buf, soff, charbuff, hasWrited, DEFAULT_BUFFER_SIZE);
				hasWrited += DEFAULT_BUFFER_SIZE;
				flush();
				slen = slen - DEFAULT_BUFFER_SIZE;
				soff = soff + DEFAULT_BUFFER_SIZE;
			}
			System.arraycopy(buf, soff, charbuff, hasWrited, slen);
			hasWrited += slen;
		} else {
			System.arraycopy(buf, off, charbuff, hasWrited, len);
			hasWrited += len;
		}
	}
	@Override
	public void write(char[] buf) {
		write(buf, 0, buf.length);
	}

	@Override
	public void write(String s, int off, int len) {
		write(s.toCharArray(), 0, len);
	}

	@Override
	public void write(String s) {
		write(s.toCharArray(), 0, s.length());
	}

	@Override
	public void print(boolean b) {
		if (b) {
			write("true");
		} else {
			write("false");
		}
	}

	@Override
	public void print(char c) {
		charbuff[hasWrited] = c;
		hasWrited++;
		checkFlush();
	}

	@Override
	public void print(int i) {
		write(i);
	}

	@Override
	public void print(long l) {
		write(Long.toString(l));
	}

	@Override
	public void print(float f) {
		write(Float.toString(f));
	}

	@Override
	public void print(double d) {
		write(Double.toString(d));
	}

	@Override
	public void print(char[] s) {
		write(s);
	}

	@Override
	public void print(String s) {
		write(s);
	}

	@Override
	public void print(Object obj) {
		write(obj.toString());
	}

	@Override
	public void println() {
		write(LINE_SEP);
	}

	@Override
	public void println(boolean x) {
		print(x);
		println();
	}

	@Override
	public void println(char x) {
		print(x);
		println();
	}

	@Override
	public void println(int x) {
		print(x);
		println();
	}

	@Override
	public void println(long x) {
		print(x);
		println();
	}

	@Override
	public void println(float x) {
		print(x);
		println();
	}

	@Override
	public void println(double x) {
		print(x);
		println();
	}

	@Override
	public void println(char[] x) {
		print(x);
		println();
	}

	@Override
	public void println(String x) {
		print(x);
		println();
	}

	@Override
	public void println(Object x) {
		print(x);
		println();
	}

	@Override
	public PrintWriter printf(String format, Object... args) {
		flush();
		super.printf(format, args);
		flush();
		return this;
	}

	@Override
	public PrintWriter printf(Locale l, String format, Object... args) {
		flush();
		super.printf(l, format, args);
		flush();
		return this;
	}

	@Override
	public PrintWriter append(CharSequence csq) {
		write(csq.toString());
		return this;
	}

	@Override
	public PrintWriter append(CharSequence csq, int start, int end) {
		write(csq.toString().substring(start, end));
		return this;
	}

	@Override
	public PrintWriter append(char c) {
		write(c);
		return this;
	}
	
	@Override
	public PrintWriter format(String format, Object... args) {
		flush();
		super.format(format, args);
		flush();
		return this;
	}
	@Override
	public PrintWriter format(Locale l, String format, Object... args) {
		flush();
		super.format(l, format, args);
		flush();
		return this;
	}
	public void checkFlush() {
		if (hasWrited == DEFAULT_BUFFER_SIZE) {
			flush();
		}
	}

}
