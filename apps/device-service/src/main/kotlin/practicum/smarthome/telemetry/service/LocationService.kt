package practicum.smarthome.telemetry.service

import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import practicum.smarthome.telemetry.model.CreateLocationRequest
import practicum.smarthome.telemetry.model.Device
import practicum.smarthome.telemetry.model.Location
import practicum.smarthome.telemetry.model.UpdateLocationRequest
import practicum.smarthome.telemetry.repository.LocationRepository
import java.time.Instant

@Service
class LocationService(
    private val repository: LocationRepository,
    private val deviceService: DeviceService,
) {
    suspend fun create(request: CreateLocationRequest): Location {
        val now = Instant.now()
        val saved = repository.save(
            Location(
                userId = request.userId,
                parentId = request.parentId,
                name = request.name,
                createdAt = now,
                updatedAt = now,
            )
        )
        return saved
    }

    fun findAll(): Flow<Location> =
        repository.findAllByOrderByCreatedAtDesc()

    suspend fun findById(id: Long): Location =
        repository.findById(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Location $id not found")

    suspend fun update(id: Long, request: UpdateLocationRequest): Location {
        val existing = repository.findById(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Location $id not found")

        val saved = repository.save(
            existing.copy(
                name = request.name,
                parentId = request.parentId,
                updatedAt = Instant.now()
            )
        )
        return saved
    }

    suspend fun delete(id: Long) {
        if (!repository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Location $id not found")
        }
        repository.deleteById(id)
    }

    suspend fun findAllDevices(id: Long): Flow<Device> =
        deviceService.findByLocationId(id)
}
