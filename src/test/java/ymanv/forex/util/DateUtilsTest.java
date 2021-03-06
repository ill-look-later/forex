/**
 * Copyright (C) 2015 https://github.com/ymanv
 *
 * This software is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package ymanv.forex.util;

import static org.assertj.core.api.Assertions.assertThat;
import static ymanv.forex.util.DateUtils.DATE_TIME_WITH_TZ;

import java.util.Date;

import org.junit.Test;

import ymanv.forex.util.DateUtils;

public class DateUtilsTest {

	@Test
	public void testToUTC() throws Exception {
		// given
		Date date = DATE_TIME_WITH_TZ.parse("2015-09-12 18:50:26.55 CEST");

		// when
		Date actual = DateUtils.toUTC(date);

		assertThat(actual).hasSameTimeAs(DATE_TIME_WITH_TZ.parse("2015-09-12 18:50:26.55 UTC"));
		assertThat(actual).hasSameTimeAs(DATE_TIME_WITH_TZ.parse("2015-09-12 20:50:26.55 CEST"));
	}

	@Test
	public void testNextDay() throws Exception {
		// given
		Date date = DATE_TIME_WITH_TZ.parse("2015-09-12 18:50:26.55 CEST");

		// when
		Date actual = DateUtils.nextDay(date);

		assertThat(actual).hasSameTimeAs(DATE_TIME_WITH_TZ.parse("2015-09-13 18:50:26.55 CEST"));
	}

	@Test
	public void testGetNbOfMonths() throws Exception {
		Date date1 = DATE_TIME_WITH_TZ.parse("2015-02-05 18:50:26.55 CET");
		Date date2 = DATE_TIME_WITH_TZ.parse("2015-08-05 18:50:26.55 CEST");

		int result = DateUtils.getNbOfMonths(date1, date2);

		assertThat(result).isEqualTo(6);
	}
	
	@Test
	public void testGetNbOfMonths_JustBelowSixMonths() throws Exception {
		Date date1 = DATE_TIME_WITH_TZ.parse("2015-02-05 18:50:26.55 CET");
		Date date2 = DATE_TIME_WITH_TZ.parse("2015-08-05 18:50:26.54 CEST");

		int result = DateUtils.getNbOfMonths(date1, date2);

		assertThat(result).isEqualTo(5);
	}
	
	@Test
	public void testGetNbOfMonths_DatesfromDifferentYears() throws Exception {
		Date date1 = DATE_TIME_WITH_TZ.parse("2014-12-31 18:50:26.55 CET");
		Date date2 = DATE_TIME_WITH_TZ.parse("2015-01-01 18:50:26.54 CET");

		int result = DateUtils.getNbOfMonths(date1, date2);

		assertThat(result).isEqualTo(0);
	}
}