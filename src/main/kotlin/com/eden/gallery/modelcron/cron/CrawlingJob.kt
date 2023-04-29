package com.eden.gallery.modelcron.cron

import com.eden.gallery.modelcron.service.ConfigService
import com.eden.gallery.modelcron.service.CrawlService
import com.eden.gallery.modelcron.utils.ConfigKey
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

/**
 * Crawling jobs for site mrcong.com
 */
@ConditionalOnProperty(
    value = ["app.scheduling.enable"], havingValue = "true", matchIfMissing = true
)
@Component
class CrawlingJob(
    @Autowired val crawlService: CrawlService,
    @Autowired val configService: ConfigService,
) : Logging {

    val site = "https://mrcong.com/page/"

    /**
     * Crawl mrcong.com for data, run every 15 secs.
     */
    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.SECONDS)
    fun crawlFullSize() {

        val page: Int = configService.findConfig(ConfigKey.CURR_PAGE.name)?.value?.toInt() ?: 0
        logger.info("job craw full page run at: ${LocalDateTime.now()}")
        if (page > 0) {
            logger.info("crawling page $page")
            crawlService.crawlSite(site, page)
            configService.saveConfig(ConfigKey.CURR_PAGE.name, (page - 1).toString())
        } else {
            val ids = listOf(3, 2, 1)
            ids.stream().forEach { idx ->
                logger.info("crawling page $page")
                crawlService.crawlSite(site, idx)
            }
        }
    }

    /**
     * Converted classify tags to model data.
     */
    @Scheduled(fixedRate = 15, timeUnit = TimeUnit.SECONDS)
    fun convertTagToModel() {
        logger.info("job convert tags to model run at: ${LocalDateTime.now()}")
        val count = crawlService.convertTagsToModels(200)
        logger.info("converted $count tags to model.")
    }

    /**
     * Crawl model images to database.
     */
    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    fun crawlModelImages() {

        logger.info("job crawl model images run at: ${LocalDateTime.now()}")
        val name = crawlService.crawlForModelImage()
        if (StringUtils.hasText(name)) {
            logger.info("successfully crawl data for model $name")
        } else {
            logger.info("no model to crawl")
        }
    }
}
