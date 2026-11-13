package practicum.smarthome.telemetry.service

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class TelemetryListener(
    private val service: TelemetryService,
) {
    @EventListener(ApplicationReadyEvent::class)
    fun listen() {
        // Здесь должна быть интеграция с MQTT клиентом, а затем сохранения показания
        // service.create(payload)
    }
}
