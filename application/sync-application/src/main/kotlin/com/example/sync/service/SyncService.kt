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

    fun syncForecastInfo(baseDate: String) :String{
        val restTemplate = RestTemplate()
        val apiUrl = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"
        val serviceKey = "5ujUAyGTxZ442ja0WByzWCh/laRSESQzH/S4nJegNAggfLaVOb6yYPBbEdyoz5HS56J167m3Z03IAcuqp0GUYg=="


        val uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
            .queryParam("serviceKey",serviceKey)
            .queryParam("pageNo","1")
            .queryParam("numOfRows","809")
            .queryParam("dataType","JSON")
            .queryParam("base_date",baseDate)
            .queryParam("base_time","0500")
            .queryParam("nx","62")
            .queryParam("ny","130")


        val url = uriBuilder.build().toUri();

        println("url = ${url.toString()}")

        return try {
            val response: ResponseEntity<String> = restTemplate.exchange(url, HttpMethod.GET, null, String::class.java)
            if (response.body != null) {
                val weatherForecastList = convertJsonToDto(response.body!!)
                weatherService.saveAll(weatherForecastList.map { it.toEntity() })
                "성공"
            } else {
                "실패"
            }
        } catch (e: NullPointerException) {
            "요청하신 $baseDate 해당 날짜의 데이터가 api에서 제공되지 않았습니다."
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
