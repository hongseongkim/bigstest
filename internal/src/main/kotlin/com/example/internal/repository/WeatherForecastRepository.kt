package com.example.internal.repository
import com.example.domain.entity.WeatherForecast
import org.springframework.data.jpa.repository.JpaRepository


interface WeatherForecastRepository : JpaRepository<WeatherForecast,Long> {
    fun findAllByFcstDateAndFcstTime(baseDate: String, baseTime: String): List<WeatherForecast>?
    fun findAllByFcstDate(fcstDate: String): List<WeatherForecast>?

}