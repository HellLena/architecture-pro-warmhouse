package practicum.smarthome.telemetry.model

data class CreateDeviceRequest(
    val userId: Long,
    val serialNumber: String,
    val name: String?,
    val type: DeviceType,
    val status: DeviceState = DeviceState.ACTIVE,
)

data class UpdateDeviceRequest(
    val locationId: Long,
    val name: String,
    val status: DeviceState,
)

enum class DeviceType {
    TEMPERATURE_SENSOR, LIGHT_SENSOR, GATE_SENSOR, VIDEO_CAMERA
}

enum class DeviceState {
    ACTIVE,
    INACTIVE,
}

data class CreateLocationRequest(
    val userId: Long,
    val parentId: Long? = null,
    val name: String,
)

data class UpdateLocationRequest(
    val parentId: Long? = null,
    val name: String,
)
