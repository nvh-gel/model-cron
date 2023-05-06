package com.eden.gallery.modelcron.cron

import com.eden.gallery.modelcron.service.ConfigService
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

/**
 * Cron to keep dev api alive.
 */
@ConditionalOnProperty(
    value = ["app.scheduling.enable"], havingValue = "true", matchIfMissing = true
)
@Component
class KeepAliveJob(
    @Autowired val template: RestTemplate,
    @Autowired val configService: ConfigService,
) : Logging {

    /**
     * Job to keep staging api alive.
     */
    @Scheduled(fixedRate = 14, timeUnit = TimeUnit.MINUTES)
    fun keepStagingGalleryApiAlive() {
        val now = LocalDateTime.now()
        val enable: Boolean = configService.findConfig("KEEP_ALIVE_STAGING")?.value.toBoolean()
        if (enable && now.hour < 17) {
            template.getForEntity("https://model-gallery-api-staging.onrender.com/healthz", String::class.java)
            logger.info("job keep gallery dev api success at: $now")
        }
    }

    /**
     * Job to keep staging api alive.
     */
    @Scheduled(fixedRate = 14, timeUnit = TimeUnit.MINUTES)
    fun keepProdGalleryApiAlive() {
        val now = LocalDateTime.now()
        val enable: Boolean = configService.findConfig("KEEP_ALIVE_PROD")?.value.toBoolean()
        if (enable && now.hour < 17) {
            template.getForEntity("https://model-gallery-api.onrender.com/healthz", String::class.java)
            logger.info("job keep gallery dev api success at: $now")
        }
    }

    /**
     * Job to keep staging cron alive.
     */
    @Scheduled(fixedRate = 14, timeUnit = TimeUnit.MINUTES)
    fun keepCronAlive() {
        template.getForEntity("https://model-cron.onrender.com/healthz", String::class.java)
        logger.info("job keep cron dev success at: ${LocalDateTime.now()}")
    }
}
