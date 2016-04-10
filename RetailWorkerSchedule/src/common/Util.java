package common;

public class Util {

	/**
	 * convert int to enum Days.
	 * 
	 * @param num
	 * @return
	 */
	public static eDays numToDay(int num) {
		eDays day = eDays.Max;

		switch (num)
		{
		case 0:
			day = eDays.Monday;
			break;
		case 1:
			day = eDays.Tues;
			break;
		case 2:
			day = eDays.Wed;
			break;
		case 3:
			day = eDays.Thur;
			break;
		case 4:
			day = eDays.Fri;
			break;
		case 5:
			day = eDays.Sat;
			break;
		case 6:
			day = eDays.Sun;
			break;
		default:
			break;
		}

		return day;
	}

	/**
	 * convert enum day to int
	 * 
	 * @param day
	 * @return
	 */
	public static int dayToNum(eDays day) {

		int num = 0;

		switch (day)
		{
		case Monday:
			num = 0;
			break;
		case Tues:
			num = 1;
			break;
		case Wed:
			num = 2;
			break;
		case Thur:
			num = 3;
			break;
		case Fri:
			num = 4;
			break;
		case Sat:
			num = 5;
			break;
		case Sun:
			num = 6;
			break;
		default:
			break;
		}

		return num;
	}
}
