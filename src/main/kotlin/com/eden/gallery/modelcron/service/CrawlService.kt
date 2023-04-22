package com.eden.gallery.modelcron.service

/**
 * Service for crawling.
 */
interface CrawlService {

    /**
     * Crawl a site by page.
     */
    fun crawlSite(site: String, page: Int): Boolean
}
