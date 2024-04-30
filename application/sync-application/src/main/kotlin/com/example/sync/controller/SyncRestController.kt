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



            val inputDate = LocalDate.parse(baseDate, DateTimeFormatter.BASIC_ISO_DATE)
        if (inputDate.isAfter(today.minusDays(3)) && inputDate.isBefore(today.plusDays(1))) {
            return syncService.syncForecastInfo(baseDate);
        }
                return "입력하신 baseDate: $baseDate 는 오늘을 포함한 최근 3일 이여야 합니다."


    }

}