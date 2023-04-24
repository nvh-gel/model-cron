package com.eden.gallery.modelcron.document

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Data for tags.
 */
@Suppress("unused")
@Document
class Tag(

    @Id
    val id: ObjectId = ObjectId(),
    val tag: String = "",
    val url: String = "",
    val publisher: Boolean = false,
    val category: Boolean = false,
    var converted: Boolean = false,
) {

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
