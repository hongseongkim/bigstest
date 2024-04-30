package com.example.domain.service

import com.example.domain.entity.WeatherForecast
import org.springframework.stereotype.Service


interface WeatherService {
    fun getForecast(fcstDate: String): List<WeatherForecast>
//    fun getForecast(fcstDate: String,fcstTime: String): List<WeatherForecast>
    fun saveAll(weatherForecastList: List<WeatherForecast>)
}