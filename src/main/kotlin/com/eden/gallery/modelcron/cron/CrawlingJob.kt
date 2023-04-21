package com.eden.gallery.modelcron.cron

import com.eden.gallery.modelcron.service.ConfigService
import com.eden.gallery.modelcron.service.CrawlService
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

@Component
class CrawlingJob(
    @Autowired val crawlService: CrawlService,
    @Autowired val configService: ConfigService,
) : Logging {

    val SITE: String = "https://mrcong.com/page/"

    @Scheduled(fixedRate = 15, timeUnit = TimeUnit.SECONDS)
    fun crawlFullSize() {
        var currentPage = 1
        var maxPage = 1
        val currentPageConfig = configService.findConfig("CURR_PAGE")
        val maxPageConfig = configService.findConfig("MAX_PAGE")
        if (currentPageConfig != null) {
            currentPage = currentPageConfig.value.toInt()
        }
        if (maxPageConfig != null) {
            maxPage = maxPageConfig.value.toInt()
        }
        logger.info(LocalDateTime.now().toString())
        if (currentPage < maxPage) {
            crawlService.crawlSite(SITE, currentPage)
            configService.saveConfig("CURR_PAGE", (currentPage + 1).toString())
        }
    }
}
