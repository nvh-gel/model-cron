package com.eden.gallery.modelcron.service

import com.eden.gallery.modelcron.document.Tag

/**
 * Service for tags handling.
 */
interface TagService {

    /**
     * Create a tag from request.
     */
    fun createTag(tag: Tag): Boolean

    /**
     * Create multiple tags from a list.
     */
    fun createTag(tags: Set<Tag>): Boolean

    /**
     * Find all model tags by paging.
     */
    fun findModelTags(page: Int, size: Int): List<Tag>

    /**
     * Update a list of tags.
     */
    fun saveAll(tags: List<Tag>): Int
}
