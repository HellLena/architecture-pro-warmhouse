package practicum.smarthome.telemetry.repository

import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import practicum.smarthome.telemetry.model.Device

interface DeviceRepository : CoroutineCrudRepository<Device, Long> {
    fun findAllByOrderByCreatedAtDesc(): Flow<Device>

    fun findAllByUserIdOrderByCreatedAtDesc(userId: Long): Flow<Device>

    fun findAllByLocationIdOrderByCreatedAtDesc(locationId: Long): Flow<Device>
}
