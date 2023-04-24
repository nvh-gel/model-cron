package com.eden.gallery.modelcron.document

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Data for model.
 */
@Suppress("unused")
@Document
class Model(
    @Id var id: ObjectId = ObjectId.get(),
    val name: String,
    val url: String,
    val images: List<String> = ArrayList(),
    val rel: List<String> = ArrayList(),
    val fc: Int = 0,
    val bb: Int = 0,
    val wa: Int = 0,
    val hi: Int = 0,
    val bd: Int = 0,
    val sx: Int = 0,
    val ct: Int = 0,
    val avg: Float = 0.0f,
    val skip: Boolean = false,
    val needCrawl: Boolean = true,
) {
}
