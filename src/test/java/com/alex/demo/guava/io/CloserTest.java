package com.alex.demo.guava.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.google.common.io.ByteSource;
import com.google.common.io.Closer;
import com.google.common.io.Files;

public class CloserTest {

	@Test
	public void testCloser() throws IOException {
		ByteSource byteSource = Files.asByteSource(new File("D:/IntelliJIDEA_Workspace/com.alex.demo/guava-demo/src/test/resources/io/files.PNG"));
		Closer closer = Closer.create();
		try {
			InputStream inputStream = closer.register(byteSource.openStream());
		} catch (Throwable e) {
			throw closer.rethrow(e);
		} finally {
			closer.close();
		}
	}

	/**
	 * 搞清楚try catch finally 整个流程的执行顺序
	 */
	@Test(expected = RuntimeException.class)
	public void testTryCatchFinally() {
		try {
			System.out.println("work area.");
			throw new IllegalArgumentException();
		} catch (Exception e) {
			System.out.println("exception area");
			throw new RuntimeException();// 先执行finally，再抛出这个异常
		} finally {
			System.out.println("finally area");
		}
	}

	@Test
	public void testTryCatch() {
		Throwable t = null;
		try {
			throw new RuntimeException("1");
		} catch (Exception e) {
			t = e;
			throw e;
		} finally {
			try {
				// close
				throw new RuntimeException("2");
			} catch (Exception e) {
				t.addSuppressed(e);
			}
		}
	}
}
