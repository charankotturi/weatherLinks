package com.application.weatherlinks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.application.weatherlinks.databinding.ActivityName2Binding;

import java.util.Objects;

public class NameActivity2 extends AppCompatActivity {

    private ActivityName2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityName2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.cardNext.setOnClickListener(click -> {
            if (Objects.requireNonNull(binding.etName.getText()).toString().length() != 0){
                Intent intent = new Intent(this, MainActivity2.class);
                intent.putExtra("Name", binding.etName.getText().toString());
                startActivity(intent);
            }
        });
    }
}