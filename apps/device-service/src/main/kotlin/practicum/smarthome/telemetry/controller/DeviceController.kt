package practicum.smarthome.telemetry.controller

import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import practicum.smarthome.telemetry.model.CreateDeviceRequest
import practicum.smarthome.telemetry.model.Device
import practicum.smarthome.telemetry.model.UpdateDeviceRequest
import practicum.smarthome.telemetry.service.DeviceService

@RestController
@RequestMapping("/api/devices")
class DeviceController(
    private val service: DeviceService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun create(@RequestBody request: CreateDeviceRequest): Device =
        service.create(request)

    @GetMapping
    fun findAll(): Flow<Device> = service.findAll()

    @GetMapping("by-user/{userId}") // временное решение, с точки зрения REST API этот метод здесь быть не должен
    suspend fun findAllByUserId(userId: Long): Flow<Device> = service.findAllByUserId(userId)

    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id: Long): Device =
        service.findById(id)

    @PutMapping("/{id}")
    suspend fun update(
        @PathVariable id: Long,
        @RequestBody request: UpdateDeviceRequest
    ): Device = service.update(id, request)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    suspend fun delete(@PathVariable id: Long) {
        service.delete(id)
    }
}
