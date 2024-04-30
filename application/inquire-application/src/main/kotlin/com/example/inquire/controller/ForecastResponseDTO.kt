package com.example.inquire.controller

data class ForecastResponseDTO(
    val category: String,
    val fcstDate: String,
    val fcstTime: String,
    val fcstValue: String
)