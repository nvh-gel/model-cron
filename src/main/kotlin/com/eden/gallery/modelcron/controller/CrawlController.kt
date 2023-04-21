package com.eden.gallery.modelcron.controller

import com.eden.gallery.modelcron.service.CrawlService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/crawl")
class CrawlController(
    @Autowired val crawlService: CrawlService,
) {
    val site: String = "https://mrcong.com/page/"
    val defaultPage: Int = 1

    @GetMapping
    fun crawlSite(): String {

        return crawlService.crawlSite(site, defaultPage)
    }

    @GetMapping("/{page}")
    fun crawlSite(@PathVariable page: Int): String {

        return crawlService.crawlSite(site, page)
    }
}
