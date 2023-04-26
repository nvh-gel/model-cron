package com.eden.gallery.modelcron.cron

import org.apache.logging.log4j.kotlin.Logging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

/**
 * Cron to keep dev api alive.
 */
@Component
class KeepAliveJon : Logging {
    val template: RestTemplate = RestTemplate()

    @Scheduled(fixedRate = 14, timeUnit = TimeUnit.MINUTES)
    fun keepDevGalleryApiAlive() {

        template.getForEntity("https://gallery-dev.onrender.com/healthz", String::class.java)
        logger.info("job keep gallery dev api success at: ${LocalDateTime.now()}")
    }

    @Scheduled(fixedRate = 14, timeUnit = TimeUnit.MINUTES)
    fun keepCronAlive() {

        template.getForEntity("https://model-cron.onrender.com/healthz", String::class.java)
        logger.info("job keep cron dev success at: ${LocalDateTime.now()}")
    }
}
