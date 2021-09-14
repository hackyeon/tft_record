package com.hackyeon.tftrecord

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import com.hackyeon.tftrecord.data.DataObject.API_KEY
import com.hackyeon.tftrecord.data.DataObject.BASE_URL
import com.hackyeon.tftrecord.data.DataObject.retrofit
import com.hackyeon.tftrecord.data.DataObject.retrofitService
import com.hackyeon.tftrecord.data.DataObject.sendSummonerData
import com.hackyeon.tftrecord.data.Summoner
import com.hackyeon.tftrecord.databinding.ActivityMainBinding
import com.hackyeon.tftrecord.service.RetrofitService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var inputMethodManager: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        connectRetrofit()
        createListener()
    }

    private fun initView(){
        inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    }

    private fun connectRetrofit(){
        var httpClient = OkHttpClient().newBuilder()
        httpClient.addInterceptor(Interceptor { chain ->
            var request = chain.request().newBuilder()
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36")
                .addHeader("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
                .addHeader("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Origin", "https://developer.riotgames.com")
                .addHeader("X-Riot-Token", API_KEY)
                .build()
            chain.proceed(request)
        })

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

        retrofitService = retrofit.create(RetrofitService::class.java)
    }

    private fun createListener() {
        binding.searchSummonerEditText.setOnKeyListener { _, i, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_UP && i == KeyEvent.KEYCODE_ENTER) {
                inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                searchSummoner()
                return@setOnKeyListener true
            }
            false
        }
    }

    private fun searchSummoner(){
        retrofitService.getSummoner(binding.searchSummonerEditText.text.toString()).enqueue(object: Callback<Summoner>{
            override fun onResponse(call: Call<Summoner>, response: Response<Summoner>) {
                if(response.isSuccessful){
                    var body = response.body()!!
                    sendSummonerData(body.id, body.accountId, body.puuid, body.name, body.profileIconId, body.summonerLevel)
                    var intent = Intent(this@MainActivity, MatchActivity::class.java)
                    startActivity(intent)
                    binding.searchSummonerEditText.text.clear()
                }
            }

            override fun onFailure(call: Call<Summoner>, t: Throwable) {
            }
        })
    }

}