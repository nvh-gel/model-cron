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
}
