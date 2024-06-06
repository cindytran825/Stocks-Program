import org.junit.Before;
import org.junit.Test;

import model.MyDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This is a test class for the MyDate test.
 */
public class MyDateTest {
  MyDate day1;
  MyDate day2;

  @Before
  public void setUp() {
    day1 = new MyDate(1, 1, 0);
    day2 = new MyDate(6, 5, 2024);
  }

  @Test
  public void testValidMyDate() {
    // Jan 12 2001
    MyDate jan122001 = new MyDate(12, 1, 2001);
    assertEquals("2001-01-12", jan122001.toString());

    // Feb 29 2004
    MyDate feb292004 = new MyDate(29, 2, 2004);
    assertEquals("2004-02-29", feb292004.toString());

    // Feb 29 2000
    MyDate feb292000 = new MyDate(29, 2, 2000);
    assertEquals("2000-02-29", feb292000.toString());

    // Dec 31 1659
    MyDate dec311659 = new MyDate(31, 12, 1659);
    assertEquals("1659-12-31", dec311659.toString());

    // Jun 30 1342
    MyDate jun301342 = new MyDate(30, 6, 1342);
    assertEquals("1342-06-30", jun301342.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNegativeDayMyDate() {
    // Jan -1 2000
    MyDate illegalJan = new MyDate(-1, 1, 2000);
    assertEquals("2000-01--01", illegalJan.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTooLargeDayMyDate() {
    // Jan 1000 2000
    MyDate illegalJan = new MyDate(1000, 1, 2000);
    assertEquals("2000-01-11", illegalJan.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNegativeMonthMyDate() {
    // -May 1 2000
    MyDate illegalJan = new MyDate(1, -5, 2000);
    assertEquals("2000--5--01", illegalJan.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTooLargeMonthMyDate() {
    // OctoSeptember 5 2000
    MyDate illegalJan = new MyDate(5, 19, 2000);
    assertEquals("2000-19-05", illegalJan.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNegativeYearMyDate() {
    // Jan 19 -2000
    MyDate illegalJan = new MyDate(19, 1, -2000);
    assertEquals("2000-01-19", illegalJan.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLeapYearMyDate1() {
    // feb 29 1999
    MyDate illegalJan = new MyDate(29, 2, 1999);
    assertEquals("1999-02-29", illegalJan.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLeapYearMyDate2() {
    // feb 29 2300
    MyDate illegalJan = new MyDate(29, 2, 2300);
    assertEquals("2300-02--29", illegalJan.toString());
  }

  @Test
  public void testAdvance1() {
    day2.advance(1000);
    assertEquals("2027-01-31", day2.toString());

    day1.advance(-100);
    assertEquals("0000-01-01", day1.toString());

    day2.advance(-1000);
    assertEquals("2024-05-06", day2.toString());

    day1.advance(739377);
    assertEquals("2024-05-06", day1.toString());

    day2.advance(-739377);
    assertEquals("0000-01-01", day2.toString());

    day1.advance(-1000000);
    assertEquals("0000-01-01", day1.toString());
  }

  @Test
  public void testAdvance2() {
    day1.advance(1);
    assertEquals("0000-01-02", day1.toString());

    day1.advance(30);
    assertEquals("0000-02-01", day1.toString());

    day1.advance(29);
    assertEquals("0000-03-01", day1.toString());

    day1.advance(365);
    assertEquals("0001-03-01", day1.toString());
  }

  @Test
  public void testToString() {
    assertEquals("0000-01-01", day1.toString());
    assertEquals("2024-05-06", day2.toString());
  }

  @Test
  public void testEquals() {
    MyDate day = new MyDate(1, 1, 0);
    assertTrue(day1.equals(day));
    assertTrue(day.equals(day1));
    assertTrue(day.equals(day));

    day.advance(1);
    assertFalse(day.equals(day1));

    assertFalse(day2.equals(day1));
    assertFalse(day1.equals(day2));
  }

  @Test
  public void testCompare() {
    MyDate day = new MyDate(17, 12, 2014);
    MyDate other = new MyDate(1, 6, 2020);
    assertEquals(-1993, day.compareTo(other));
    assertEquals(1993, other.compareTo(day));

    assertEquals(739377, day2.compareTo(day1));
    assertEquals(-739377, day1.compareTo(day2));

    assertEquals(0, day1.compareTo(day1));
  }

  @Test
  public void testGetDay() {
    assertEquals(1, day1.getDay());
    day1.advance(1);
    assertEquals(2, day1.getDay());
    assertEquals(6, day2.getDay());
    day2.advance(-1);
    assertEquals(5, day2.getDay());
  }

  @Test
  public void testGetMonth() {
    assertEquals(1, day1.getMonth());
    day1.advance(31);
    assertEquals(2, day1.getMonth());
    assertEquals(5, day2.getMonth());
    day2.advance(-31);
    assertEquals(4, day2.getMonth());
  }

  @Test
  public void testGetYear() {
    assertEquals(0, day1.getYear());
    day1.advance(366);
    assertEquals(1, day1.getYear());
    assertEquals(2024, day2.getYear());
    day2.advance(-367);
    assertEquals(2023, day2.getYear());
  }


}