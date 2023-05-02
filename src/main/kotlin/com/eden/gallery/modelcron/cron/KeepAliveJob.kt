package com.eden.gallery.modelcron.cron

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
    @Autowired val template: RestTemplate
) : Logging {

    /**
     * Job to keep staging api alive.
     */
    @Scheduled(fixedRate = 14, timeUnit = TimeUnit.MINUTES)
    fun keepStagingGalleryApiAlive() {

        template.getForEntity("https://model-gallery-api-staging.onrender.com/healthz", String::class.java)
        logger.info("job keep gallery dev api success at: ${LocalDateTime.now()}")
    }

    /**
     * Job to keep staging api alive.
     */
    @Scheduled(fixedRate = 14, timeUnit = TimeUnit.MINUTES)
    fun keepProdGalleryApiAlive() {

        template.getForEntity("https://model-gallery-api.onrender.com/healthz", String::class.java)
        logger.info("job keep gallery dev api success at: ${LocalDateTime.now()}")
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
