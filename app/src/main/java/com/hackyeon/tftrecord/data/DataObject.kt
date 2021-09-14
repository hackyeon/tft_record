package com.hackyeon.tftrecord.data

import com.hackyeon.tftrecord.service.RetrofitService
import retrofit2.Retrofit

object DataObject {
    const val API_KEY = "RGAPI-48465945-e50c-49d0-b132-f1af82de5c08"
    const val BASE_URL = "https://kr.api.riotgames.com/"
    const val GET_SUMMONER = "tft/summoner/v1/summoners/by-name/{summonerName}"

    // 통신
    lateinit var retrofit: Retrofit
    lateinit var retrofitService: RetrofitService

    // 검색정보
    var id: String? = null
    var accountId: String? = null
    var puuid: String? = null
    var name: String? = null
    var profileIconId: Int? = null
    var summonerLevel: Int? = null

    fun sendSummonerData(id: String, accountId: String, puuid: String, name: String, profileIconId: Int, summonerLevel: Int){
        this.id = id
        this.accountId = accountId
        this.puuid = puuid
        this.name = name
        this.profileIconId = profileIconId
        this.summonerLevel = summonerLevel
    }



}