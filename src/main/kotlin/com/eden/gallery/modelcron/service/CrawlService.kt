package com.eden.gallery.modelcron.service

/**
 * Service for crawling.
 */
interface CrawlService {

    /**
     * Crawl a site by page.
     */
    fun crawlSite(site: String, page: Int): Boolean

    /**
     * Convert tags to models data.
     */
    fun convertTagsToModels(size: Int): Int

    /**
     * Crawl full list of model image.
     */
    fun crawlForModelImage(): String?
}
