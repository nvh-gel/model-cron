package com.eden.gallery.modelcron.document

import com.eden.nosql.model.BaseDocument
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

/**
 * Data for album.
 */
@Suppress("unused")
@Document("album")
class Album(
    val name: String = "",
    val url: String = "",
    val thumbnail: String = "",
    val tags: Set<String> = HashSet(),
    val download: Set<String> = HashSet(),
) : BaseDocument(ObjectId.get(), UUID.randomUUID(), false, LocalDateTime.now(), LocalDateTime.now())
