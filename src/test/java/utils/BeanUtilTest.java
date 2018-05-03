package utils;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

import utils.BeanUtil;
import java.sql.Date;

public class BeanUtilTest {
	
	@Test
	public void testRequestedDateFormatterWithTextDate() {
		String textDate = "2017-01-01";
		Date sqlDate = Date.valueOf(textDate);
		assertThat(BeanUtil.requestedDateFormatter(textDate), is(sqlDate));
	}
	
	@Test
	public void testRequestedDateFormatterWithEmptyValue() {
		String textDate = "";
		Date sqlDate = null;
		assertThat(BeanUtil.requestedDateFormatter(textDate), is(sqlDate));
	}
	
	@Test
	public void testConvertStringToDoubleEmptyValue() {
		String value = "";
		Double result = null;
		assertThat(BeanUtil.convertStringToDouble(value), is(result));
	}
	
	@Test
	public void testConvertStringToDoubleWithDigits() {
		String value = "7 200,89";
		Double result = 7200.89;
		assertThat(BeanUtil.convertStringToDouble(value), is(result));
	}
	
	@Test
	public void testConvertStringToDoubleWithLetters() {
		String value = "23frg";
		Double result = 23.0;
		assertThat(BeanUtil.convertStringToDouble(value), is(result));
	}
	
	@Test
	public void testConvertStringToDoubleWithMinus() {
		String value = "-7 200,89";
		Double result = -7200.89;
		assertThat(BeanUtil.convertStringToDouble(value), is(result));
	}
	
	@Test
	public void testConvertStringToDoubleWithDigits2() {
		String value = "7200.89";
		Double result = 7200.89;
		assertThat(BeanUtil.convertStringToDouble(value), is(result));
	}
	
	@Test
	public void testConvertStringToDoubleWithDigits3() {
		String value = "-7200.89";
		Double result = -7200.89;
		assertThat(BeanUtil.convertStringToDouble(value), is(result));
	}
	
	@Test
	public void testConvertNullToDouble3() {
		Double value = null;
		Double result = 0d;
		assertThat(BeanUtil.convertNullToDouble(value), is(result));
	}
	
}
