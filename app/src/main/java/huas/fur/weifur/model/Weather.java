package huas.fur.weifur.model;

import android.graphics.Bitmap;

public class Weather {

	public static String choice_city="101250601";//默认为常德
	public static String city;
	public static String forecase_date;
	public static String current_date_time;
	public static String current_condition;
	public static String current_temp;
	public static String current_humidity;
	public static String current_image_url;
	public static Bitmap current_image;
	public static String current_wind;
	
	public static Forecast[] day = new Forecast[5];

	
	static {
		for (int i = 0; i< day.length; i++){
			day[i] = new Forecast();
		}
	}
	
	public static String GetSmsMsg(){
		String msg = "";
		msg += city + "天气预报：今日 ";
		msg += current_condition + "," + current_temp+".\n";
		msg += "未来3天天气预报：\n";
		for (int i=0;i<3;i++)
		{
			msg+=day[i].day_of_week+"," + day[i].condition + "," +
		day[i].high + "~" + day[i].low+"\n";
		}

		return msg;
	}


}
