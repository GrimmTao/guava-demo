package com.alex.demo.guava.utilities;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import java.nio.charset.Charset;

import org.junit.Test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;

/***************************************
 * @author:Alex Wang
 * @Date:2017/10/7
 * @QQ: 532500648
 ***************************************/
public class StringsTest {

	@Test
	public void testStringsMethod() {
		assertThat(Strings.emptyToNull(""), nullValue());
		assertThat(Strings.nullToEmpty(null), equalTo(""));
		assertThat(Strings.nullToEmpty("hello"), equalTo("hello"));
		assertThat(Strings.commonPrefix("Hello", "Hit"), equalTo("H"));// 公共前缀
		assertThat(Strings.commonPrefix("Hello", "Xit"), equalTo(""));
		assertThat(Strings.commonSuffix("Hello", "Echo"), equalTo("o"));// 公共后缀
		assertThat(Strings.repeat("Alex", 3), equalTo("AlexAlexAlex"));
		assertThat(Strings.isNullOrEmpty(null), equalTo(true));
		assertThat(Strings.isNullOrEmpty(""), equalTo(true));

		assertThat(Strings.padStart("Alex", 3, 'H'), equalTo("Alex"));// 在前面追加
		assertThat(Strings.padStart("Alex", 5, 'H'), equalTo("HAlex"));
		assertThat(Strings.padEnd("Alex", 5, 'H'), equalTo("AlexH"));// 在后面追加
	}

	@Test
	public void testCharsets() {
		Charset charset = Charset.forName("UTF-8");
		assertThat(Charsets.UTF_8, equalTo(charset));
	}

	/**
	 * functor
	 */
	@Test
	public void testCharMatcher() {
		assertThat(CharMatcher.javaDigit().matches('5'), equalTo(true));
		assertThat(CharMatcher.javaDigit().matches('x'), equalTo(false));

		assertThat(CharMatcher.is('A').countIn("Alex Sharing the Google Guava to Us"), equalTo(1));// 大写的A有几个
		assertThat(CharMatcher.breakingWhitespace().collapseFrom("      hello Guava     ", '*'), equalTo("*hello*Guava*"));
		assertThat(CharMatcher.javaDigit().or(CharMatcher.whitespace()).removeFrom("hello 234 world"), equalTo("helloworld"));// 把数字和空格移除
		assertThat(CharMatcher.javaDigit().or(CharMatcher.whitespace()).retainFrom("hello 234 world"), equalTo(" 234 "));// 保留数字和空格

	}

	public Integer text() {
		return 0;
	}
}
