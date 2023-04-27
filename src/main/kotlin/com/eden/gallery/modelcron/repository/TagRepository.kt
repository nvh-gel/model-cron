package com.eden.gallery.modelcron.repository

import com.eden.gallery.modelcron.document.Tag
import org.bson.types.ObjectId
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * Repository for tags.
 */
@Repository
interface TagRepository : MongoRepository<Tag, ObjectId> {

    /**
     * Check if tag with name existed.
     */
    fun existsByTag(tag: String): Boolean

    /**
     * Find a list of tags by tag names.
     */
    fun findAllByTagIn(tags: List<String>): List<Tag>

    /**
     * Find a list of unprocessed tags.
     */
    fun findAllByConvertedIsFalse(pageable: Pageable): List<Tag>
}
