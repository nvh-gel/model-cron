package com.eden.gallery.modelcron.document

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Suppress("unused")
@Document("album")
class Album(

    @Id
    var id: ObjectId = ObjectId(),
    val name: String = "",
    val url: String = "",
    val tags: List<String> = ArrayList(),
)
