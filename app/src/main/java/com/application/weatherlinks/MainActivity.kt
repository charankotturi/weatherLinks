package com.application.weatherlinks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.weatherlinks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardBack.setOnClickListener {
            val intent = Intent(this, NameActivity::class.java)
            startActivity(intent)
        }

        val name = intent.getStringExtra("Name")
        binding.textView2.text = "$name enter your city name!"

        binding.cardNext.setOnClickListener {
            if (binding.etCityName.text.toString().isNotEmpty()){
                val intent = Intent(this, LocationActivity::class.java)
                intent.putExtra("Location", binding.etCityName.text.toString())
                startActivity(intent)
            }
        }
    }
}