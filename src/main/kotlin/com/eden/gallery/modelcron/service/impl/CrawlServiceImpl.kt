package com.eden.gallery.modelcron.service.impl

import com.eden.gallery.modelcron.document.Album
import com.eden.gallery.modelcron.document.Model
import com.eden.gallery.modelcron.document.Tag
import com.eden.gallery.modelcron.repository.CategoryRepository
import com.eden.gallery.modelcron.repository.PublisherRepository
import com.eden.gallery.modelcron.service.AlbumService
import com.eden.gallery.modelcron.service.CrawlService
import com.eden.gallery.modelcron.service.ModelService
import com.eden.gallery.modelcron.service.TagService
import org.apache.logging.log4j.kotlin.Logging
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StringUtils
import java.net.URL

/**
 * Implementation for crawling service
 */
@Service
class CrawlServiceImpl(
    @Autowired val albumService: AlbumService,
    @Autowired val tagService: TagService,
    @Autowired val modelService: ModelService,
    @Autowired val publisherRepository: PublisherRepository,
    @Autowired val categoryRepository: CategoryRepository,
    private val publishers: List<String> = publisherRepository.findAll().map { p -> p.name }.toList(),
    private val categories: List<String> = categoryRepository.findAll().map { c -> c.name }.toList(),
) : CrawlService, Logging {

    /**
     * Crawl a site by page.
     */
    override fun crawlSite(site: String, page: Int): Boolean {

        val link = site + page
        val doc: Document = Jsoup.parse(URL(link), 3000)
        val articles: List<Element> = doc.select("article")

        val albums: List<Album> = articles.stream().map(this::mapArticleToAlbum).toList()
        albumService.createAlbum(albums)
        val tags: Set<Tag> = articles.flatMap { a -> mapArticleToTags(a) }.toSet()
        tagService.createTag(tags)

        return true
    }

    /**
     * Convert crawled tags to models
     */
    @Transactional(readOnly = false)
    override fun convertTagsToModels(size: Int): Int {

        val tags = tagService.findModelTags(0, size)
        if (tags.isNotEmpty()) {
            val models = tags.parallelStream()
                .filter { tag -> !publishers.contains(tag.tag) && !categories.contains(tag.tag) }
                .map { tag: Tag -> Model(name = tag.tag, url = tag.url) }
                .toList()
            val result = modelService.saveAll(models)

            tags.forEach { tag: Tag -> tag.converted = true }
            tagService.saveAll(tags)

            return result
        }
        return 0
    }

    /**
     * Crawl for model images.
     */
    override fun crawlForModelImage(): String? {

        val model = modelService.findModelForCrawling() ?: return null
        val url = model.url
        val modelPage = Jsoup.parse(URL(url), 3000)
        val articles: List<Element> = modelPage.select("article")

        val images = articles.map { article ->
            article.getElementsByTag("img").first()?.attr("src") ?: ""
        }.toList()
        model.images = images.filter(StringUtils::hasText).toSet()
        model.numberOfAlbum = images.size

        val relatedTags = articles.flatMap { article ->
            article.getElementsByAttributeValue("rel", "tag")
                .map { tag -> tag.html() }
                .filter { tag -> model.name != tag }
        }.toSet()
        model.rel = relatedTags

        model.needCrawl = false
        modelService.save(model)
        return model.name
    }

    /**
     * Convert an article to tags.
     */
    private fun mapArticleToTags(element: Element): Set<Tag> {
        return element.getElementsByAttributeValue("rel", "tag")
            .map { e -> Tag(tag = e.html(), url = e.attr("href")) }
            .toSet()
    }

    /**
     * Convert an article to album.
     */
    private fun mapArticleToAlbum(element: Element): Album {

        val title: Element = element.getElementsByTag("h2")[0]
        val link: Element = title.getElementsByTag("a")[0]

        val name: String = link.html()
        val url: String = link.attr("href")
        val thumbnail: String = element.getElementsByTag("img").first()?.attr("src") ?: ""
        val tags: Set<String> = element.getElementsByAttributeValue("rel", "tag")
            .map(Element::html).toSet()

        return Album(name = name, url = url, thumbnail = thumbnail, tags = tags)
    }
}
