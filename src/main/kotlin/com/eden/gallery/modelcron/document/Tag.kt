package com.eden.gallery.modelcron.document

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Suppress("unused")
@Document
class Tag(

    @Id
    val id: ObjectId = ObjectId(),
    val tag: String = "",
    val url: String = "",
    val cat: String = "",
) {
}
