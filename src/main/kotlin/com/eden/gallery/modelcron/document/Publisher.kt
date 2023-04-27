package com.eden.gallery.modelcron.document

import com.eden.nosql.model.BaseDocument
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

/**
 * Mongo document for album publisher.
 */
@Suppress("unused")
@Document
class Publisher(
    val name: String = "",
    val url: String = "",
) : BaseDocument(ObjectId.get(), UUID.randomUUID(), false, LocalDateTime.now(), LocalDateTime.now())
