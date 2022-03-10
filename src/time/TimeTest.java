package time;

import static org.junit.Assert.assertFalse; 

import static org.junit.Assert.assertTrue; 
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {
	
	// good test for milliseconds
	@Test
	public void testGetMillisecondsGood() {
		int milliseconds = Time.getMilliseconds("10:20:30:007");
		assertTrue("milliseconds were not calculated properly", milliseconds == 7);
	}
	
	//first boundary test for milliseconds
	@Test
	public void testGetMillisecondsBoundary1() {
		int milliseconds = Time.getMilliseconds("10:10:10:000");
		assertTrue("milliseconds were not calculated properly", milliseconds == 0);
	}
	
	//second boundary test for milliseconds
	@Test
	public void testGetMillisecondsBoundary2() {
		int milliseconds = Time.getMilliseconds("10:10:10:999");
		assertTrue("milliseconds were not calculated properly", milliseconds == 999);
	}
	
	//bad test for milliseconds
	@Test
	public void testGetMilliSecondsBad() {
		{
			assertThrows(
					StringIndexOutOfBoundsException.class, 
					()-> {Time.getMilliseconds("10:10:00");});
			}
		}
	

	//good test for total seconds
	@Test
	void testGetTotalSecondsGood(){
		int seconds = Time.getTotalSeconds("05:05:05");
		assertTrue("The seconds were not calculated properly", seconds==18305);
		}
	
	//boundary test for total seconds
	@Test
	void testGetTotalSecondsBoundary() {
		int seconds = Time.getTotalSeconds("05:05:05");
		assertTrue("The seconds were not calculated properly", seconds==18305);
	}
	
	//bad test for total seconds
	@Test
	void testGetTotalSecondsBad() {
		{
			assertThrows(
					StringIndexOutOfBoundsException.class, 
					()-> {Time.getTotalSeconds("10:00");});
			}
		}
	
	
	// Parameterized test for seconds
	@ParameterizedTest
	@ValueSource(strings = { "05:05:00", "05:05:05", "05:05:59" })
	void testGetTotalSecondsGoodBoundary(String secondsVal) {
	int seconds = Time.getSeconds(secondsVal);
	assertTrue("The seconds were not calculated properly", seconds == 0 || 
			seconds == 5 || seconds == 59);
	}
	

	//bad test for seconds
	@Test
	void testGetSecondsBad() {
		assertFalse(Time.isSeconds("00:00:1234"));
		}

	
	// Parameterized test for minutes
	@ParameterizedTest
	@ValueSource(strings = { "03:10:00", "03:10:30", "03:10:59" })
	void testGetTotalMinutesGoodBoundary(String minuteVal) {
	int minutes = Time.getTotalMinutes(minuteVal);
	assertTrue("The minutes were not calculated properly", minutes == 10);
	}
	
	
	//bad test for minutes
	@Test
	void testGetTotalMinutesBad() {
		assertFalse(Time.isTotalMinutes("00:48:00"));
		}


	// Parameterized test for hours
	@ParameterizedTest
	@ValueSource(strings = { "05:00:00", "05:30:30", "05:59:59" })
	void testGetTotalHoursGoodBoundary(String hourVal) {
	int hours = Time.getTotalHours(hourVal);
	assertTrue("The hours were not calculated properly", hours == 5);
	}
	
	
	//bad test for hours
	@Test
	void testGetTotalHoursBad() {
		assertFalse(Time.isTotalHours("32:00:00"));
		}
}
