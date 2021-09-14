package com.hackyeon.tftrecord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.hackyeon.tftrecord.data.DataObject.name
import com.hackyeon.tftrecord.data.DataObject.profileIconId
import com.hackyeon.tftrecord.data.DataObject.summonerLevel
import com.hackyeon.tftrecord.databinding.ActivityMatchBinding

class MatchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMatchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load("http://ddragon.leagueoflegends.com/cdn/11.18.1/img/profileicon/${profileIconId}.png")
            .centerCrop()
            .into(binding.iconImageView)

        binding.nameTextView.text = name
        binding.levelTextView.text = summonerLevel.toString()

    }
}