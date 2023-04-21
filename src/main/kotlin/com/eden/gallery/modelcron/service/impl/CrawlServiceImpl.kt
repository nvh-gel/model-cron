package com.eden.gallery.modelcron.service.impl

import com.eden.gallery.modelcron.document.Album
import com.eden.gallery.modelcron.document.Tag
import com.eden.gallery.modelcron.service.AlbumService
import com.eden.gallery.modelcron.service.CrawlService
import com.eden.gallery.modelcron.service.TagService
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CrawlServiceImpl(
    @Autowired val albumService: AlbumService,
    @Autowired val tagService: TagService,
) : CrawlService {

    override fun crawlSite(site: String, page: Int): String {

        val link = site + page
        val doc: Document = Jsoup.connect(link).get()
        val articles: List<Element> = doc.select("article")

        val albums: List<Album> = articles.stream().map(this::mapArticleToAlbum).toList()
        albumService.createAlbum(albums)
        val tags: List<Tag> = articles.stream().flatMap { a -> mapArticleToTags(a).stream() }.toList()
        tagService.createTag(tags)
        return "success"
    }

    private fun mapArticleToTags(element: Element): List<Tag> {
        return element.getElementsByAttributeValue("rel", "tag")
            .map { e -> Tag(tag = e.html(), url = e.attr("href")) }
            .toList()
    }

    private fun mapArticleToAlbum(element: Element): Album {

        val title: Element = element.getElementsByTag("h2")[0]
        val link: Element = title.getElementsByTag("a")[0]
        val name: String = link.html()
        val url: String = link.attr("href")
        val tags: List<String> = element.getElementsByAttributeValue("rel", "tag")
            .map(Element::html)
        return Album(name = name, url = url, tags = tags)
    }
}
