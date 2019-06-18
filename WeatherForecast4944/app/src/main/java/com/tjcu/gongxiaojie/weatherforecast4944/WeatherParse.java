package com.tjcu.gongxiaojie.weatherforecast4944;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherParse {
    private float curTemp = 0;

    private static final String MAIN_KEY = "main";
    private static final String CUR_TEMP_KEY = "temp";
    private static final String WEATHER_KEY = "weather";
    private static final String PIC_CODE_KEU = "icon";
    private String picCode;
    public void setData(String weatherInfo){

        try {
            JSONObject object = new JSONObject(weatherInfo);
            object = object.getJSONObject(MAIN_KEY);
            curTemp = (float) object.getDouble(CUR_TEMP_KEY);

            JSONObject objectForPic = new JSONObject(weatherInfo);
            JSONArray ja = objectForPic.getJSONArray(WEATHER_KEY);
            if(ja.length() == 0){
                picCode = null;
            }else {
                picCode = ja.getJSONObject(0).getString(PIC_CODE_KEU);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Bitmap getWeatherPic(String picName){
        String url = "http://openweathermap.org/img/w/" + picName + ".png";
        return downloadPic(url);
    }

    private Bitmap downloadPic(String durl) {
        if(durl == null){
            return null;
        }
        try {
            URL url = new URL(durl);
            HttpURLConnection connerction = (HttpURLConnection)url.openConnection();
            InputStream is = connerction.getInputStream();
            Bitmap bm = BitmapFactory.decodeStream(is);
            is.close();
            return bm;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public float getCurTemp(){
        return curTemp;
    }

    public String getPicCode() {
        return picCode;
    }

}
