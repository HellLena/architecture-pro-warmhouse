package practicum.smarthome.telemetry.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("device_telemetry")
data class DeviceTelemetry(
    @Id
    val id: Long? = null,
    @Column("device_id")
    val deviceId: Long,
    @Column("payload")
    val payload: String,
    @Column("created_at")
    val createdAt: Instant,
)
