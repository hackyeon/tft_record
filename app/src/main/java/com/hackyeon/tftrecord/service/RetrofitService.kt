package com.hackyeon.tftrecord.service

import com.hackyeon.tftrecord.data.DataObject.GET_SUMMONER
import com.hackyeon.tftrecord.data.Summoner
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET(GET_SUMMONER)
    fun getSummoner(@Path("summonerName")summonerName: String): Call<Summoner>

}