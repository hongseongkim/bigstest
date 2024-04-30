package com.example.sync.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import com.fasterxml.jackson.databind.ObjectMapper
import com.example.domain.entity.WeatherForecast
import com.example.domain.service.WeatherService
import com.example.WeatherForecastDto
import org.springframework.web.util.UriComponentsBuilder

@Service
class SyncService(
    private val weatherService: WeatherService

) {

    private val objectMapper = ObjectMapper()

    fun syncForecastInfo(baseDate: String) {
        val restTemplate = RestTemplate()

        val uriBuilder = UriComponentsBuilder.fromHttpUrl("https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst")
            .queryParam("serviceKey","5ujUAyGTxZ442ja0WByzWCh/laRSESQzH/S4nJegNAggfLaVOb6yYPBbEdyoz5HS56J167m3Z03IAcuqp0GUYg==")
            .queryParam("pageNo","1")
            .queryParam("numOfRows","809")
            .queryParam("dataType","JSON")
            .queryParam("base_date",baseDate)
            .queryParam("base_time","0500")
            .queryParam("nx","62")
            .queryParam("ny","130")

        val url = uriBuilder.build().toUri()

        try {
            val response: ResponseEntity<String> = restTemplate.exchange(url, HttpMethod.GET, null, String::class.java)
            response.body?.let { jsonData ->
                val weatherForecastList = convertJsonToDto(jsonData)
                weatherService.saveAll(weatherForecastList.map { it.toEntity() }) // DTO를 엔티티로 변환하여 저장
            }
        } catch (e: Exception) {

            e.printStackTrace()
        }
    }

    private fun convertJsonToDto(jsonData: String?): List<WeatherForecastDto> {
        val weatherForecastList = mutableListOf<WeatherForecastDto>()
        if (!jsonData.isNullOrEmpty()) {
            val rootNode = objectMapper.readTree(jsonData)
            val itemListNode = rootNode["response"]["body"]["items"]["item"]
            for (itemNode in itemListNode) {
                val baseDate = itemNode["baseDate"].asText()
                val baseTime = itemNode["baseTime"].asText()
                val category = itemNode["category"].asText()
                val fcstDate = itemNode["fcstDate"].asText()
                val fcstTime = itemNode["fcstTime"].asText()
                val fcstValue = itemNode["fcstValue"].asText()
                val nx = itemNode["nx"].asInt()
                val ny = itemNode["ny"].asInt()

                val weatherForecast = WeatherForecastDto(
                    baseDate = baseDate,
                    baseTime = baseTime,
                    category = category,
                    fcstDate = fcstDate,
                    fcstTime = fcstTime,
                    fcstValue = fcstValue,
                    nx = nx,
                    ny = ny
                )
                weatherForecastList.add(weatherForecast)
            }
        }
        return weatherForecastList
    }




    private fun WeatherForecastDto.toEntity(): WeatherForecast {
        return WeatherForecast(
            baseDate = this.baseDate,
            baseTime = this.baseTime,
            category = this.category,
            fcstDate = this.fcstDate,
            fcstTime = this.fcstTime,
            fcstValue = this.fcstValue,
            nx = this.nx,
            ny = this.ny
        )
    }
}
