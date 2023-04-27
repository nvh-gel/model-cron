package com.eden.gallery.modelcron.repository

import com.eden.gallery.modelcron.document.Category
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * Mongo repository for category.
 */
@Repository
interface CategoryRepository : MongoRepository<Category, ObjectId>
