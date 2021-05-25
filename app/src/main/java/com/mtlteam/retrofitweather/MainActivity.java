package com.mtlteam.retrofitweather;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import com.mtlteam.retrofitweather.rest.service.client.ApiWeatherRestClient;
import com.mtlteam.retrofitweather.rest.service.event.GetWeatherResponseEvent;
import com.mtlteam.retrofitweather.rest.service.model.Weather;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    private static final double LATITUDE = 51.75924850;
    private static final double LONGITUDE = 19.45598330;

    @BindView(R.id.textView)
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ApiWeatherRestClient.getInstance().cancelGetWeather();
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        ApiWeatherRestClient.getInstance().cancelGetWeather();
    }

    @OnClick(R.id.button)
    public void onClickButton() {
        showProgressDialog("Weather data is loading...");
        ApiWeatherRestClient.getInstance().getWeather(LATITUDE, LONGITUDE);
        Timber.d("Start getting weather data");
    }

    @Subscribe
    public void onGetWeatherResponseEvent(GetWeatherResponseEvent event) {
        closeProgressDialog();

        if (!event.isCanceled()) {
            if (event.hasResponse()) {
                Weather weather = event.getResponse();
                resultTextView.setText(weather.toString());
                Timber.d("Received weather data.");
            } else {
                resultTextView.setText("No Weather Data found!");
                Timber.d("Error getting weather data.");
            }
        } else {
            Timber.d("Canceled getting weather data.");
        }
    }

}