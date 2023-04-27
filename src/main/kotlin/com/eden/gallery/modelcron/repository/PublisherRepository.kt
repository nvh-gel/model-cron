package com.eden.gallery.modelcron.repository

import com.eden.gallery.modelcron.document.Publisher
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * Repository for publishers.
 */
@Repository
interface PublisherRepository : MongoRepository<Publisher, ObjectId>
