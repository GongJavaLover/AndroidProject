package com.tjcu.gongxiaojie.weatherforecast4944;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DataGet {

    private static final  String WEATHER_URL ="http://api.openweathermap.org/data/2.5/weather?";
    private static final  String MyKey = "1b87fb9151d3f2da484743d38bdc3d1c";

    public class WeatherResult{

        private float curTemp;
        private int errorCode;
        private String cityName;
        private Bitmap icon;

        public Bitmap getIcon() {
            return icon;
        }

        public void setIcon(Bitmap icon) {
            this.icon = icon;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public float getCurTemp() {
            return curTemp;
        }

        public void setCurTemp(float curTemp) {
            this.curTemp = curTemp;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

    }
    private float tranfromToC(float abs){
        return (abs - 273.15f);
    }
    public WeatherResult getWeatherDate(String cityName){
        WeatherResult weatherResult = new WeatherResult();
        try{
            String urlSting = WEATHER_URL + "q=" + cityName + "&APPID=" + MyKey;
            Log.i("DateGet",urlSting);
            URL url = new URL(urlSting);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream in = connection.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();
            int c = 0;
            while ((c = in.read())!= -1){
                stringBuffer.append((char)c);
            }
            Log.i("DataGet",stringBuffer.toString());

            WeatherParse weatherParse = new WeatherParse();
            weatherParse.setData(stringBuffer.toString());
            weatherResult.setCurTemp(tranfromToC(weatherParse.getCurTemp()));
            weatherResult.setCityName(cityName);
            //图标
            weatherResult.setIcon(weatherParse.getWeatherPic(weatherParse.getPicCode()));

            weatherResult.setErrorCode(0);
            return weatherResult;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherResult;
    }
}

