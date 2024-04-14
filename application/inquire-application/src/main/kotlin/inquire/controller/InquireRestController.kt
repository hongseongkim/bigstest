package com.example.inquire.controller

import com.example.domain.service.WeatherService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class InquireRestController(
    private val weatherService: WeatherService
) {


    @GetMapping("/inquire")
    fun inquire(
        @RequestParam("baseDate") fcstDate: String): ResponseEntity<Any> {

        val weatherForecasts = weatherService.getForecast(fcstDate)

        return if (weatherForecasts.isEmpty()) {
            ResponseEntity.status(HttpStatus.NO_CONTENT).body("조회하신 날짜의 데이터가 존재하지않습니다.")
        } else {
            val forecastResponseDTO = weatherForecasts.map { forecast ->
                ForecastResponseDTO(
                    category = forecast.category,
                    fcstDate = forecast.fcstDate,
                    fcstTime = forecast.fcstTime,
                    fcstValue = forecast.fcstValue
                )
            }
            ResponseEntity.ok(forecastResponseDTO)
        }
    }





}