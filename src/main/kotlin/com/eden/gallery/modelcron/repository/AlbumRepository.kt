package com.eden.gallery.modelcron.repository

import com.eden.gallery.modelcron.document.Album
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AlbumRepository : MongoRepository<Album, ObjectId> {
    fun existsByName(name: String): Boolean
}
