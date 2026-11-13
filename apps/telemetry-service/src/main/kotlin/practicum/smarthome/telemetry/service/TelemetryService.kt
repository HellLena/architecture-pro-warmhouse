package practicum.smarthome.telemetry.service

import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service
import practicum.smarthome.telemetry.model.DeviceTelemetry
import practicum.smarthome.telemetry.repository.TelemetryRepository
import java.time.Instant

@Service
class TelemetryService(
    private val repository: TelemetryRepository
) {
    fun findAll(
        deviceId: Long,
        dateFrom: Instant,
        dateTo: Instant,
    ): Flow<DeviceTelemetry> =
        repository.findAllByDeviceIdAndCreatedAtBetweenOrderByCreatedAtDesc(deviceId, dateFrom, dateTo)
}
