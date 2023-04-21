package com.eden.gallery.modelcron.service

interface CrawlService {

    fun crawlSite(site: String, page: Int): String
}
