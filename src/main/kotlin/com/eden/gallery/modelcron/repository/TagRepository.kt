package com.eden.gallery.modelcron.repository

import com.eden.gallery.modelcron.document.Tag
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TagRepository : MongoRepository<Tag, ObjectId> {

    fun existsByTag(tag: String): Boolean
}
