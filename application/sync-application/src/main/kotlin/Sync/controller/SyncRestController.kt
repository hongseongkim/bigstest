package com.example.com.example.Sync.controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import com.example.com.example.Sync.service.SyncService
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RestController
class SyncRestController(
    private val syncService: SyncService
) {

    @PostMapping("/sync/{baseDate}")
    fun sync(@PathVariable baseDate: String): String {


        val today = LocalDate.now()

        try {

            val inputDate = LocalDate.parse(baseDate, DateTimeFormatter.BASIC_ISO_DATE)
            if (inputDate != today && inputDate != today.plusDays(1) && inputDate != today.minusDays(1)) {
                return "입력한 $baseDate 는 전날,오늘일,다음날 중 하나여야 합니다."
            }

            syncService.syncForecastInfo(baseDate)

        } catch (e: Exception) {
            return "데이터를 저장하는데 실패하셨습니다.: ${e.message}"
        }

        return "성공";

    }

}