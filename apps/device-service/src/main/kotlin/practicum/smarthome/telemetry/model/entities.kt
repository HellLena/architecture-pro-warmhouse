package practicum.smarthome.telemetry.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("device_group")
data class DeviceGroup(
    @Id
    val id: Long? = null,
    @Column("user_id")
    val userId: Long,
    @Column("name")
    val name: String,
    @Column("created_at")
    val createdAt: Instant,
    @Column("updated_at")
    val updatedAt: Instant
)

@Table("location")
data class Location(
    @Id
    val id: Long? = null,
    @Column("user_id")
    val userId: Long,
    @Column("name")
    val name: String,
    @Column("parent_id")
    val parentId: Long? = null,
    @Column("created_at")
    val createdAt: Instant,
    @Column("updated_at")
    val updatedAt: Instant
)

@Table("device")
data class Device(
    @Id
    val id: Long? = null,
    @Column("user_id")
    val userId: Long,
    @Column("serial_number")
    val serialNumber: String,
    @Column("name")
    val name: String?,
    @Column("type")
    val type: DeviceType,
    @Column("state")
    val state: DeviceState,
    @Column("location_id")
    val locationId: Long? = null,
    @Column("created_at")
    val createdAt: Instant,
    @Column("updated_at")
    val updatedAt: Instant
)
