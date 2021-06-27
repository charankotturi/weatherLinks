package com.application.weatherlinks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.application.weatherlinks.databinding.ActivityLocation2Binding;
import com.application.weatherlinks.models.model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LocationActivity2 extends AppCompatActivity {

    private ActivityLocation2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLocation2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.cardBack.setOnClickListener(click -> {
            Intent intent = new Intent(this, NameActivity2.class);
            startActivity(intent);
        });

        String city = getIntent().getStringExtra("Location");

        Retrofit builder = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherInfo call = builder.create(WeatherInfo.class);

        if (city.length() != 0){
            call.getInfo(city, "8225e411e7c5d47fca34ed6ee34508f6").enqueue(new Callback<model>() {
                @Override
                public void onResponse(Call<model> call, Response<model> response) {
                    setText(response.body());
                }

                @Override
                public void onFailure(Call<model> call, Throwable t) {
                    Toast.makeText(LocationActivity2.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void setText(model response) {
        binding.textView.setText("Temperature : "+response.getMain().getTemp()+" °C");
        binding.textView3.setText("Humidity : "+response.getMain().getHumidity()+" %");
        binding.textView4.setText("Pressure : "+response.getMain().getPressure() + "millibars");
        binding.textView5.setText("Weather\nDescription : "+ response.getWeather().get(0).getDescription());
    }
}