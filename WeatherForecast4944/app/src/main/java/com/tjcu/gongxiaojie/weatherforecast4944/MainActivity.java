package com.tjcu.gongxiaojie.weatherforecast4944;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText mEditWeather;
    TextView mTextCityName,mTextTemp;
    ImageView mImageWeather;
    Button mButtonFresh;
    WeatherTask mCurrentTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditWeather = (EditText) findViewById(R.id.edit_weather);
        mTextCityName = (TextView) findViewById(R.id.txt_cityname);
        mTextTemp = (TextView) findViewById(R.id.txt_temp);
        mImageWeather = (ImageView) findViewById(R.id.weather_pic);
        mButtonFresh = (Button) findViewById(R.id.btn);

        mButtonFresh.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mCurrentTask = new WeatherTask();
                mCurrentTask.execute(mEditWeather.getText().toString());
            }
        });

    }
    class WeatherTask extends AsyncTask<String,Void,DataGet.WeatherResult> {

        protected DataGet.WeatherResult doInBackground(String...params){
            DataGet dataGet = new DataGet();
            return dataGet.getWeatherDate(params[0]);
        }

        @Override
        protected void onPostExecute(DataGet.WeatherResult weatherResult) {
            super.onPostExecute(weatherResult);
            if (weatherResult.getErrorCode() == 0){
                DecimalFormat decimalFormat = new DecimalFormat("00.00");
                String temp = decimalFormat.format(weatherResult.getCurTemp());
                mTextTemp.setText(temp+"℃");
                mTextCityName.setText("City:" + weatherResult.getCityName());
                mImageWeather.setImageBitmap(weatherResult.getIcon());
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (mCurrentTask!=null){
            mCurrentTask.cancel(true);
            mCurrentTask = null;
        }
        super.onDestroy();
    }
}
