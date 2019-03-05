package com.example.hajunfeng.weather_h;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.MonthDay;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("hint");
        builder.setMessage("Successful refresh");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        new DownloadUpdate().execute();
    }

    private class DownloadUpdate extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            String stringUrl = "http://t.weather.sojson.com/api/weather/city/101040100";
            HttpURLConnection urlConnection = null;
            BufferedReader reader;

            try {
                URL url = new URL(stringUrl);

                // Create the request to get the information from the server, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Mainly needed for debugging
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                //The temperature
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String temperature) {
            //Update the temperature displayed
            int temp = temperature.indexOf("forecast");
            int t1 = temperature.indexOf("高温 ",temp);
            int t2 = temperature.indexOf("低温 ",temp);
            int c1 = temperature.indexOf("℃",t1);
            int c2 = temperature.indexOf("℃",t2);
            String high = temperature.substring(t1+3, c1+1);
            String low = temperature.substring(t2+3,c2);
            ((TextView) findViewById(R.id.temperature)).setText(low + "～" + high);
            t1 = temperature.indexOf("wendu");
            t2 = temperature.indexOf(",", t1);
            String wendu = temperature.substring(t1+8,t2-1);
            ((TextView) findViewById(R.id.temperature_of_the_day)).setText(wendu);

            //t1 = temperature.indexOf("date");
            t2 = temperature.indexOf("message");
            String time = temperature.substring(t2-11, t2 - 3);
            ((TextView) findViewById(R.id.tv_date)).setText(time);

            t1 = temperature.indexOf("type",temp);
            t2 = temperature.indexOf("notice",temp);
            String type = temperature.substring(t1 + 7, t2 - 3);
            switch (type){
                case "晴":((ImageView)findViewById(R.id.img_weather_condition)).setImageDrawable(getResources().getDrawable(R.drawable.sunny_small));
                    ((LinearLayout)findViewById(R.id.master)).setBackground(getResources().getDrawable(R.drawable.sunny));break;
                case "多云":((ImageView)findViewById(R.id.img_weather_condition)).setImageDrawable(getResources().getDrawable(R.drawable.partly_sunny_small));
                    ((LinearLayout)findViewById(R.id.master)).setBackground(getResources().getDrawable(R.drawable.clody_up));break;
                case "阴":((ImageView)findViewById(R.id.img_weather_condition)).setImageDrawable(getResources().getDrawable(R.drawable.windy_small));
                    ((LinearLayout)findViewById(R.id.master)).setBackground(getResources().getDrawable(R.drawable.clody_up));break;
                case "小雨":((ImageView)findViewById(R.id.img_weather_condition)).setImageDrawable(getResources().getDrawable(R.drawable.rainy_small));
                    ((LinearLayout)findViewById(R.id.master)).setBackground(getResources().getDrawable(R.drawable.rain));break;
                default: ((ImageView)findViewById(R.id.img_weather_condition)).setImageDrawable(getResources().getDrawable(R.drawable.rainy_small));break;
            }

            t1 = temperature.indexOf("type", t2);
            t2 = temperature.indexOf("notice", t1);
            type = temperature.substring(t1 + 7, t2 - 3);
            switch (type){
                case "晴":((ImageView)findViewById(R.id.p_day1)).setImageDrawable(getResources().getDrawable(R.drawable.sunny_small));break;
                case "多云":((ImageView)findViewById(R.id.p_day1)).setImageDrawable(getResources().getDrawable(R.drawable.partly_sunny_small));break;
                case "阴":((ImageView)findViewById(R.id.p_day1)).setImageDrawable(getResources().getDrawable(R.drawable.windy_small));break;
                case "小雨":((ImageView)findViewById(R.id.p_day1)).setImageDrawable(getResources().getDrawable(R.drawable.rainy_small));break;
                default: ((ImageView)findViewById(R.id.p_day1)).setImageDrawable(getResources().getDrawable(R.drawable.rainy_small));break;
            }

            t1 = temperature.indexOf("type", t2);
            t2 = temperature.indexOf("notice", t1);
            type = temperature.substring(t1 + 7, t2 - 3);
            switch (type){
                case "晴":((ImageView)findViewById(R.id.p_day2)).setImageDrawable(getResources().getDrawable(R.drawable.sunny_small));break;
                case "多云":((ImageView)findViewById(R.id.p_day2)).setImageDrawable(getResources().getDrawable(R.drawable.partly_sunny_small));break;
                case "阴":((ImageView)findViewById(R.id.p_day2)).setImageDrawable(getResources().getDrawable(R.drawable.windy_small));break;
                case "小雨":((ImageView)findViewById(R.id.p_day2)).setImageDrawable(getResources().getDrawable(R.drawable.rainy_small));break;
                default: ((ImageView)findViewById(R.id.p_day2)).setImageDrawable(getResources().getDrawable(R.drawable.rainy_small));break;
            }

            t1 = temperature.indexOf("type", t2);
            t2 = temperature.indexOf("notice", t1);
            type = temperature.substring(t1 + 7, t2 - 3);
            switch (type){
                case "晴":((ImageView)findViewById(R.id.p_day3)).setImageDrawable(getResources().getDrawable(R.drawable.sunny_small));break;
                case "多云":((ImageView)findViewById(R.id.p_day3)).setImageDrawable(getResources().getDrawable(R.drawable.partly_sunny_small));break;
                case "阴":((ImageView)findViewById(R.id.p_day3)).setImageDrawable(getResources().getDrawable(R.drawable.windy_small));break;
                case "小雨":((ImageView)findViewById(R.id.p_day3)).setImageDrawable(getResources().getDrawable(R.drawable.rainy_small));break;
                default: ((ImageView)findViewById(R.id.p_day3)).setImageDrawable(getResources().getDrawable(R.drawable.rainy_small));break;
            }

            t1 = temperature.indexOf("type", t2);
            t2 = temperature.indexOf("notice", t1);
            type = temperature.substring(t1 + 7, t2 - 3);
            switch (type){
                case "晴":((ImageView)findViewById(R.id.p_day4)).setImageDrawable(getResources().getDrawable(R.drawable.sunny_small));break;
                case "多云":((ImageView)findViewById(R.id.p_day4)).setImageDrawable(getResources().getDrawable(R.drawable.partly_sunny_small));break;
                case "阴":((ImageView)findViewById(R.id.p_day4)).setImageDrawable(getResources().getDrawable(R.drawable.windy_small));break;
                case "小雨":((ImageView)findViewById(R.id.p_day4)).setImageDrawable(getResources().getDrawable(R.drawable.rainy_small));break;
                default: ((ImageView)findViewById(R.id.p_day4)).setImageDrawable(getResources().getDrawable(R.drawable.rainy_small));break;
            }
            t1 = temperature.indexOf("week", temp);
            t2 = temperature.indexOf("fx", temp);
            String week = temperature.substring(t1+7,t2 - 3);
            ((TextView) findViewById(R.id.t_day)).setText(day(week));

            t1 = temperature.indexOf("高温 ",t2);
            t2 = temperature.indexOf("低温 ",t1);
            c1 = temperature.indexOf("℃",t1);
            c2 = temperature.indexOf("℃",t2);
            high = temperature.substring(t1+3, c1+1);
            low = temperature.substring(t2+3,c2);
            ((TextView) findViewById(R.id.t_temp1)).setText(low + "～" + high);
            t1 = temperature.indexOf("week", t2);
            t2 = temperature.indexOf("fx",t1);
            week = temperature.substring(t1 + 7, t2 - 3);
            ((TextView) findViewById(R.id.t_day1)).setText(day(week));

            t1 = temperature.indexOf("高温 ",t2);
            t2 = temperature.indexOf("低温 ",t1);
            c1 = temperature.indexOf("℃",t1);
            c2 = temperature.indexOf("℃",t2);
            high = temperature.substring(t1+3, c1+1);
            low = temperature.substring(t2+3,c2);
            ((TextView) findViewById(R.id.t_temp2)).setText(low + "～" + high);
            t1 = temperature.indexOf("week", t2);
            t2 = temperature.indexOf("fx",t1);
            week = temperature.substring(t1 + 7, t2 - 3);
            ((TextView) findViewById(R.id.t_day2)).setText(day(week));

            t1 = temperature.indexOf("高温 ",t2);
            t2 = temperature.indexOf("低温 ",t1);
            c1 = temperature.indexOf("℃",t1);
            c2 = temperature.indexOf("℃",t2);
            high = temperature.substring(t1+3, c1+1);
            low = temperature.substring(t2+3,c2);
            ((TextView) findViewById(R.id.t_temp3)).setText(low + "～" + high);
            t1 = temperature.indexOf("week", t2);
            t2 = temperature.indexOf("fx",t1);
            week = temperature.substring(t1 + 7, t2 - 3);
            ((TextView) findViewById(R.id.t_day3)).setText(day(week));

            t1 = temperature.indexOf("高温 ",t2);
            t2 = temperature.indexOf("低温 ",t1);
            c1 = temperature.indexOf("℃",t1);
            c2 = temperature.indexOf("℃",t2);
            high = temperature.substring(t1+3, c1+1);
            low = temperature.substring(t2+3,c2);
            ((TextView) findViewById(R.id.t_temp4)).setText(low + "～" + high);
            t1 = temperature.indexOf("week", t2);
            t2 = temperature.indexOf("fx",t1);
            week = temperature.substring(t1 + 7, t2 - 3);
            ((TextView) findViewById(R.id.t_day4)).setText(day(week));


        }

    }
    public String day(String w){
        switch (w){
            case "星期一": return "Monday";
            case "星期二": return "Tuesday";
            case "星期三": return "Wednesday";
            case "星期四": return "Thursday";
            case "星期五": return "Friday";
            case "星期六": return "Saturday";
            case "星期日": return "Sunday";
            default: return "Error";
        }
    }
}
