package com.eden.gallery.modelcron.document

import com.eden.nosql.model.BaseDocument
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

/**
 * Data for tags.
 */
@Document
class Tag(
    val tag: String = "",
    val url: String = "",
    var converted: Boolean = false,
) : BaseDocument(ObjectId.get(), UUID.randomUUID(), false, LocalDateTime.now(), LocalDateTime.now()) {

    /**
     * Compare two tags.
     */
    override fun equals(other: Any?): Boolean {

        return this === other ||
                other != null
                && other is Tag
                && this.tag == other.tag
                && this.url == other.url
    }

    /**
     * Hash code for comparing.
     */
    override fun hashCode(): Int {
        return tag.hashCode() * 31 + url.hashCode() * 31
    }
}
