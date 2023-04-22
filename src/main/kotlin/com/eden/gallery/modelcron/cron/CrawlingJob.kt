package com.eden.gallery.modelcron.cron

import com.eden.gallery.modelcron.document.Config
import com.eden.gallery.modelcron.service.ConfigService
import com.eden.gallery.modelcron.service.CrawlService
import com.eden.gallery.modelcron.utils.ConfigKey
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
    @Autowired val configService: ConfigService,
) : Logging {

    private final val site = "https://mrcong.com/page/"
    var maxPageConfig: Config? = null
    var maxPage: Int = 1

    /**
     * Crawl mrcong.com for data, run every 15 secs.
     */
    @Scheduled(fixedRate = 15, timeUnit = TimeUnit.SECONDS)
    fun crawlFullSize() {

        val currentPageConfig = configService.findConfig(ConfigKey.CURR_PAGE.name)
        val currentPage = currentPageConfig?.value?.toInt() ?: 1
        if (maxPageConfig == null) {
            maxPageConfig = configService.findConfig(ConfigKey.MAX_PAGE.name)
            maxPage = maxPageConfig?.value?.toInt() ?: 1
        }
        logger.info(LocalDateTime.now().toString())
        if (currentPage <= maxPage) {
            crawlService.crawlSite(site, currentPage)
            configService.saveConfig(ConfigKey.CURR_PAGE.name, (currentPage + 1).toString())
        }
    }
}
