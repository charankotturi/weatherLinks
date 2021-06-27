package com.application.weatherlinks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.weatherlinks.databinding.ActivityNameBinding

class NameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardNext.setOnClickListener {
            if (binding.etName.text.toString().isNotEmpty()){
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("Name", binding.etName.text.toString())
                startActivity(intent)
            }
        }
    }
}