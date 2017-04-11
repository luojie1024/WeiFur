package huas.fur.weifur.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import huas.fur.weifur.model.Weather;


public class WeatherAdapter {
	
	public static void GetWeatherData() throws IOException, Throwable {
    	String address = "http://api.k780.com:88/?app=weather.future&weaid="+ Weather.choice_city+"&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=xml";

    	URL aURL = new URL(address);
     	URLConnection conn = aURL.openConnection();
        conn.connect();
        InputStream is = conn.getInputStream();
         
      	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(is,"UTF-8");
        
        int dayCounter = 0;
        while(parser.next()!= XmlPullParser.END_DOCUMENT){
			// TODO: 2016/12/29    xml解析
			String element = parser.getName();
        	if (element != null && element.equals("item_0")){
        		while(true){
        			int eventCode = parser.next();
        			element =  parser.getName();
        			if (eventCode == XmlPullParser.START_TAG){
        				if ("days".equals(element)){
							Weather.current_date_time =  parser.nextText();
        				}else if ("citynm".equals(element)){
							Weather.city = parser.nextText();
						}else if ("temperature".equals(element)){
							Weather.current_temp = parser.nextText();
						}else if ("humidity".equals(element)){
							Weather.current_humidity = parser.nextText();
						}else if ("weather_icon".equals(element)){
							Weather.current_image_url = parser.nextText();
							Weather.current_image = GetURLBitmap(Weather.current_image_url);
						}else if ("weather".equals(element)){
							Weather.current_condition = parser.nextText();
						} else if ("wind".equals(element)){
							Weather.current_wind=parser.nextText();
						}
        			}
        			if ("item_0".equals(parser.getName()) &&
        					eventCode == XmlPullParser.END_TAG){
        				break;
        			}
        		}    		
        	}
        	if (element != null && ("item_1".equals(element)||"item_2".equals(element)||"item_3".equals(element)||"item_4".equals(element))){
        		while(true){
        			int eventCode = parser.next();
        			element =  parser.getName();
        			if (eventCode == XmlPullParser.START_TAG){
        				if ("week".equals(element)){
    	            		Weather.day[dayCounter].day_of_week = parser.nextText();
        				}else if ("temp_low".equals(element)){
        					Weather.day[dayCounter].low =parser.nextText();
        				}else if (element.equals("temp_high")){
        					Weather.day[dayCounter].high = parser.nextText();
        				}else if ("weather_icon".equals(element)){
        					Weather.day[dayCounter].image_url =parser.nextText();
        					Weather.day[dayCounter].image = GetURLBitmap(Weather.day[dayCounter].image_url);
        				}else if ("weather".equals(element)){
        					Weather.day[dayCounter].condition =parser.nextText();
        				}
        			}
        			if ("item_1".equals(element)||"item_2".equals(element)||"item_3".equals(element)||"item_4".equals(element)&&
        					eventCode == XmlPullParser.END_TAG){
        				dayCounter++;
        				break;
        			}
        		}    
        	}
        	
        }       
        is.close();
	}
	
	private static Bitmap GetURLBitmap(String urlString){
		
	    URL url = null;
	    Bitmap bitmap = null;
	    try {
	    	url = new URL(urlString);
	    }
	    catch (MalformedURLException e){
	      e.printStackTrace();
	    }
	    
	    try{
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.connect();
	      InputStream is = conn.getInputStream();
	      bitmap = BitmapFactory.decodeStream(is);
	      is.close();
	    }
	    catch (IOException e){
	      e.printStackTrace();
	    }
	    return bitmap;
	  }

	
}
