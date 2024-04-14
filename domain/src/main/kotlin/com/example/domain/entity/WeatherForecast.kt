package com.example.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class WeatherForecast(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

