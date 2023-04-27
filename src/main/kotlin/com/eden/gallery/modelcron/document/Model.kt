package com.eden.gallery.modelcron.document

import com.eden.nosql.model.BaseDocument
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

/**
 * Data for model.
 */
@Suppress("unused")
@Document
class Model(
    val name: String,
    val url: String,
    var images: Set<String> = HashSet(),
    var rel: Set<String> = HashSet(),
    var fc: Int = 0,
    var bb: Int = 0,
    var wa: Int = 0,
    var hi: Int = 0,
    var bd: Int = 0,
    var sx: Int = 0,
    var ct: Int = 0,
    var avg: Float = 0.0f,
    var numberOfAlbum: Int = 0,
    var skip: Boolean = false,
    var needCrawl: Boolean = true,
    var moved: Boolean = false,
) : BaseDocument(ObjectId.get(), UUID.randomUUID(), false, LocalDateTime.now(), LocalDateTime.now())
