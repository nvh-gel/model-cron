package com.eden.gallery.modelcron.cron

import com.eden.gallery.modelcron.service.CrawlService
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

/**
 * Crawling jobs for site mrcong.com
 */
@Component
class CrawlingJob(
    @Autowired val crawlService: CrawlService,
) : Logging {

    private final val site = "https://mrcong.com/page/"

    /**
     * Crawl mrcong.com for data, run every 15 secs.
     */
    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.DAYS)
    fun crawlFullSize() {

        logger.info("job craw full run at: ${LocalDateTime.now()}")
        val pages = arrayListOf(1, 2, 3)
        pages.forEach { page -> crawlService.crawlSite(site, page) }
    }

    /**
     * Converted classify tags to model data.
     */
    @Scheduled(fixedRate = 12, timeUnit = TimeUnit.HOURS)
    fun convertTagToModel() {

        logger.info("job convert tags to model run at: ${LocalDateTime.now()}")
        crawlService.convertTagsToModels(200)
    }

    /**
     * Crawl model images to database.
     */
    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    fun crawlModelImages() {

        logger.info("job crawl model images run at: ${LocalDateTime.now()}")
        crawlService.crawlForModelImage()
    }
}
