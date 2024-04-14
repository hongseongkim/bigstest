package com.example.com.example.Sync

data class WeatherForecastDto(
    var id: Long? = null,
    var baseDate: String,
    var baseTime: String,
    var category: String,
    var fcstDate: String,
    var fcstTime: String,
    var fcstValue: String,
    var nx: Int,
    var ny: Int
)

