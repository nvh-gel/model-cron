package com.eden.gallery.modelcron.document

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.HashSet

/**
 * Data for model.
 */
@Suppress("unused")
@Document
class Model(
    @Id var id: ObjectId = ObjectId.get(),
    val name: String,
    val url: String,
    var images: List<String> = ArrayList(),
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
) {
}
