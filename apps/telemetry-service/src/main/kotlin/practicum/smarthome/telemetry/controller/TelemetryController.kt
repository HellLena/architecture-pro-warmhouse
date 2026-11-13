package practicum.smarthome.telemetry.controller

import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*
import practicum.smarthome.telemetry.model.DeviceTelemetry
import practicum.smarthome.telemetry.service.TelemetryService
import java.time.Instant

@RestController
@RequestMapping("/api/devices-telemetry")
class TelemetryController(
    private val service: TelemetryService
) {
    @GetMapping
    fun findAll(
        @RequestParam("deviceId") deviceId: Long,
        @RequestParam("dateFrom") dateFrom: Instant,
        @RequestParam("dateTo") dateTo: Instant,
    ): Flow<DeviceTelemetry> = service.findAll(deviceId, dateFrom, dateTo)
}
