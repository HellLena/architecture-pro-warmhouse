package practicum.smarthome.telemetry.controller

import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import practicum.smarthome.telemetry.model.CreateLocationRequest
import practicum.smarthome.telemetry.model.Device
import practicum.smarthome.telemetry.model.Location
import practicum.smarthome.telemetry.model.UpdateLocationRequest
import practicum.smarthome.telemetry.service.LocationService

@RestController
@RequestMapping("/api/locations")
class LocationController(
    private val service: LocationService,
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun create(@RequestBody request: CreateLocationRequest): Location =
        service.create(request)

    @GetMapping
    fun findAll(): Flow<Location> = service.findAll()

    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id: Long): Location =
        service.findById(id)

    @PutMapping("/{id}")
    suspend fun update(
        @PathVariable id: Long,
        @RequestBody request: UpdateLocationRequest
    ): Location = service.update(id, request)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    suspend fun delete(@PathVariable id: Long) {
        service.delete(id)
    }

    @GetMapping("/{id}/devices")
    suspend fun findAllDevices(@PathVariable id: Long): Flow<Device> =
        service.findAllDevices(id)
}
