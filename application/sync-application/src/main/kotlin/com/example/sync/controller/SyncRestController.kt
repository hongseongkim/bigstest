package com.example.sync.controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import com.example.sync.service.SyncService
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RestController
class SyncRestController(
    private val syncService: SyncService
) {

    @PostMapping("/sync")
    fun sync( @RequestParam baseDate: String): String {


        val today = LocalDate.now()

        try {

            val inputDate = LocalDate.parse(baseDate, DateTimeFormatter.BASIC_ISO_DATE)
            if (inputDate != today && inputDate != today.plusDays(1) && inputDate != today.minusDays(1)) {
                return "입력한 $baseDate 는 어제,오늘,내일 중 하나여야 합니다."
            }

            syncService.syncForecastInfo(baseDate)

        } catch (e: Exception) {
            return "데이터를 저장하는데 실패하셨습니다.: ${e.message}"
        }

        return "성공"

    }

}