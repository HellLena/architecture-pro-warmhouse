package practicum.smarthome.telemetry.service

import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import practicum.smarthome.telemetry.model.CreateDeviceRequest
import practicum.smarthome.telemetry.model.Device
import practicum.smarthome.telemetry.model.UpdateDeviceRequest
import practicum.smarthome.telemetry.repository.DeviceRepository
import java.time.Instant

@Service
class DeviceService(
    private val repository: DeviceRepository
) {
    suspend fun create(request: CreateDeviceRequest): Device {
        val now = Instant.now()
        val saved = repository.save(
            Device(
                userId = request.userId,
                name = request.name,
                serialNumber = request.serialNumber,
                state = request.status,
                type = request.type,
                createdAt = now,
                updatedAt = now,
            )
        )
        return saved
    }

    fun findAll(): Flow<Device> =
        repository.findAllByOrderByCreatedAtDesc()

    suspend fun findAllByUserId(userId: Long): Flow<Device> =
        repository.findAllByUserIdOrderByCreatedAtDesc(userId)

    suspend fun findById(id: Long): Device =
        repository.findById(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Device $id not found")

    suspend fun update(id: Long, request: UpdateDeviceRequest): Device {
        val existing = repository.findById(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Device $id not found")

        val saved = repository.save(
            existing.copy(
                name = request.name,
                locationId = request.locationId,
                state = request.status,
                updatedAt = Instant.now()
            )
        )
        return saved
    }

    suspend fun delete(id: Long) {
        if (!repository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Device $id not found")
        }
        repository.deleteById(id)
    }

    suspend fun findByLocationId(locationId: Long): Flow<Device> =
        repository.findAllByLocationIdOrderByCreatedAtDesc(locationId)
}
