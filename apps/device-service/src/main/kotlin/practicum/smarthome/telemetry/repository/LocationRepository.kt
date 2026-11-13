package practicum.smarthome.telemetry.repository

import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import practicum.smarthome.telemetry.model.Location

interface LocationRepository : CoroutineCrudRepository<Location, Long> {
    fun findAllByOrderByCreatedAtDesc(): Flow<Location>
}
