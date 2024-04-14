package com.example.internal.serviceimpl

import com.example.domain.entity.WeatherForecast
import com.example.domain.service.WeatherService
import com.example.internal.repository.WeatherForecastRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WeatherServiceImpl @Autowired constructor(
    private val weatherForecastRepository: WeatherForecastRepository
) : WeatherService {


    override fun getForecast(fcstDate: String): List<WeatherForecast> {
        return weatherForecastRepository.findAllByFcstDate(fcstDate)
            ?: throw NoSuchElementException("해당날짜의 기상예보를 찾을 수 없습니다: $fcstDate ")
    }

    //    override fun getForecast(fcstDate: String, fcstTime: String): List<WeatherForecast> {
//        return weatherForecastRepository.findAllByFcstDateAndFcstTime(fcstDate, fcstTime)
//            ?: throw NoSuchElementException("Weather forecast not found for date: $fcstDate and time: $fcstTime")
//    }



    @Transactional
    override fun saveAll(weatherForecastList: List<WeatherForecast>) {
        weatherForecastRepository.saveAll(weatherForecastList)
    }
}
