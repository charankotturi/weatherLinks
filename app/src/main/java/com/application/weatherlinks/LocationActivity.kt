package com.application.weatherlinks

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.application.weatherlinks.databinding.ActivityLocationBinding
import com.application.weatherlinks.models.model
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocationBinding

    private val model = MutableLiveData<model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        model.observe(this){
            setText(it)
        }

        val city = intent.getStringExtra("Location").toString()

        val builder = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val call = builder.create(WeatherCall::class.java)

        if (city.isNotEmpty()){

            CoroutineScope(IO).launch {
                val response = call.getWeather(city, API_KEY)
                if (response.isSuccessful){
                    Log.i(TAG, "onCreate: ${response.body()}")
                    model.postValue(response.body())
                }else{
                    Toast.makeText(this@LocationActivity
                        , "Something wrong with the api"
                        , Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }
    }

    private fun setText(response: model?){
        binding.textView.text = "Temperature : ${response?.main?.temp.toString()} °C"
        binding.textView3.text = "Humidity : ${response?.main?.humidity.toString()} %"
        binding.textView4.text = "Pressure : ${response?.main?.pressure.toString()} millibars"
        binding.textView5.text = "Weather\nDescription : ${response?.weather?.get(0)?.description}"
    }
}