package com.eden.gallery.modelcron.repository

import com.eden.gallery.modelcron.document.Model
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * Mongodb repository for model.
 */
@Repository
interface ModelRepository : MongoRepository<Model, ObjectId> {

    /**
     * Find a single model that need crawling.
     */
    fun findFirstByNeedCrawlIsTrue() : Model?

    /**
     * Find a list of models by names in.
     */
    fun findAllByNameIn(names: List<String>): List<Model>
}
