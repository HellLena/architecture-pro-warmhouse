package practicum.smarthome.telemetry.repository

import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import practicum.smarthome.telemetry.model.DeviceTelemetry
import java.time.Instant

interface TelemetryRepository : CoroutineCrudRepository<DeviceTelemetry, Long> {
    fun findAllByDeviceIdAndCreatedAtBetweenOrderByCreatedAtDesc(
        deviceId: Long,
        dateFrom: Instant,
        dateTo: Instant,
    ): Flow<DeviceTelemetry>
}
