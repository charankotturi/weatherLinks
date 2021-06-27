package com.application.weatherlinks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.application.weatherlinks.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.cardBack.setOnClickListener(click -> {
            Intent intent = new Intent(this, NameActivity2.class);
            startActivity(intent);
        });

        String name = getIntent().getStringExtra("Name");
        binding.textView2.setText(name + " enter your city name!");

        binding.cardNext.setOnClickListener(click-> {
            if (binding.etCityName.getText().toString().length() != 0){
                Intent intent = new Intent(this, LocationActivity2.class);
                intent.putExtra("Location", binding.etCityName.getText().toString());
                startActivity(intent);
            }
        });
    }
}