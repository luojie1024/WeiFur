package huas.fur.weifur.activity;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import huas.fur.weifur.R;
import huas.fur.weifur.model.Weather;
import huas.fur.weifur.util.WeatherAdapter;

public class WeatherActivity extends Activity {

	final static int MENU_START_SERVICE= Menu.FIRST ;
	final static int MENU_STOP_SERVICE = Menu.FIRST + 1;
	final static int MENU_REFRESH = Menu.FIRST + 2;
	final static int MENU_QUIT = Menu.FIRST +3;
	private ArrayAdapter<String> arr_adapter;
	private Spinner spinner;
	private List<String> data_list;
	private TextView currentCondition;
	private TextView currentWind;
	private ImageView currentImage;
	private TextView currentCity;
          private Handler handler=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                              super.handleMessage(msg);
                              switch (msg.arg1) {
                                        case 0:
                                                  RefreshWeatherData();
                                                  break;
                              }

                    }
          };

	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_weather);
		 spinner = (Spinner) findViewById(R.id.spinner);

		 data_list = new ArrayList<String>();
		 data_list.add("changde");
		 data_list.add("beijing");
		 data_list.add("shanghai");
		 data_list.add("guangzhou");
		 data_list.add("shenzhen");
		 arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
		 arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		 spinner.setAdapter(arr_adapter);
		 spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			 @Override
			 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				 switch (position) {
					 case 0:
						 Weather.choice_city = "101250601";Reference_data();
						 break;
					 case 1:
						 Weather.choice_city = "101010100";Reference_data();
						 break;
					 case 2:
						 Weather.choice_city = "101020100";Reference_data();
						 break;
					 case 3:
						 Weather.choice_city = "101280101";Reference_data();
						 break;
					 case 4:
						 Weather.choice_city = "101280601";Reference_data();
						 break;

				 }
			 }

			 @Override
			 public void onNothingSelected(AdapterView<?> parent) {
			 }
		 });




	 }

	  @Override
	 public boolean onCreateOptionsMenu(Menu menu){

		 menu.add(0,MENU_START_SERVICE,0,"start service");//star server
		 menu.add(0,MENU_STOP_SERVICE,1,"stop service");
		 menu.add(0,MENU_REFRESH ,2,"refresh");
 		 menu.add(0,MENU_QUIT,3,"quit");
 		 return true;
	 }



	 private void RefreshWeatherData(){


		 currentCondition = (TextView)findViewById(R.id.tab_weather_current_condition);
		 currentWind = (TextView)findViewById(R.id.tab_weather_current_wind);
		 currentImage = (ImageView)findViewById(R.id.tab_weather_current_image);
		 currentCity = (TextView)findViewById(R.id.tab_weather_current_city);


			String msgCondition = "";
			msgCondition += "Temperature:" + Weather.current_temp + ", ";
			msgCondition += Weather.current_humidity ;
			currentCondition.setText(msgCondition);

		 	currentImage.setImageBitmap(Weather.current_image);
			currentWind.setText(Weather.current_wind + ", " + Weather.current_date_time);
		 	currentCity.setText(Weather.city);

			TextView forcastD1Date = (TextView)findViewById(R.id.tab_weather_d1_date);
			ImageView forcastD1Image = (ImageView)findViewById(R.id.tab_weather_d1_image);
			TextView forcastD1Temperature = (TextView)findViewById(R.id.tab_weather_d1_temperature);

			forcastD1Date.setText(Weather.day[0].day_of_week);
			forcastD1Image.setImageBitmap(Weather.day[0].image);

			String msgD1Temperature = Weather.day[0].high + "/" + Weather.day[0].low;
			forcastD1Temperature.setText(msgD1Temperature);

			TextView forcastD2Date = (TextView)findViewById(R.id.tab_weather_d2_date);
			ImageView forcastD2Image = (ImageView)findViewById(R.id.tab_weather_d2_image);
			TextView forcastD2Temperature = (TextView)findViewById(R.id.tab_weather_d2_temperature);

			forcastD2Date.setText(Weather.day[1].day_of_week);
			forcastD2Image.setImageBitmap(Weather.day[1].image);

			String msgD2Temperature = Weather.day[1].high + "/" + Weather.day[1].low;
			forcastD2Temperature.setText(msgD2Temperature);

			TextView forcastD3Date = (TextView)findViewById(R.id.tab_weather_d3_date);
			ImageView forcastD3Image = (ImageView)findViewById(R.id.tab_weather_d3_image);
			TextView forcastD3Temperature = (TextView)findViewById(R.id.tab_weather_d3_temperature);

			forcastD3Date.setText(Weather.day[2].day_of_week);
			forcastD3Image.setImageBitmap(Weather.day[2].image);

			String msgD3Temperature = Weather.day[2].high + "/" + Weather.day[2].low;
			forcastD3Temperature.setText(msgD3Temperature);


			TextView forcastD4Date = (TextView)findViewById(R.id.tab_weather_d4_date);
			ImageView forcastD4Image = (ImageView)findViewById(R.id.tab_weather_d4_image);
			TextView forcastD4Temperature = (TextView)findViewById(R.id.tab_weather_d4_temperature);
			
			forcastD4Date.setText(Weather.day[3].day_of_week);
			forcastD4Image.setImageBitmap(Weather.day[3].image);
			
			String msgD4Temperature = Weather.day[3].high + "/" + Weather.day[3].low;
			forcastD4Temperature.setText(msgD4Temperature);
			
	 }

	 public void Reference_data(){
		 Thread thread=new Thread(new Runnable() {
			 @Override
			 public void run() {
				 try {
					 WeatherAdapter.GetWeatherData();
				 } catch (Throwable throwable) {
					 throwable.printStackTrace();
				 }
                       Message msg = handler.obtainMessage();
                       msg.arg1 = 0;
                       handler.sendMessage(msg);
			 }
		 });thread.start();
	 }
}
