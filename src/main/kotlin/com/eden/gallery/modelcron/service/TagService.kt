package com.eden.gallery.modelcron.service

import com.eden.gallery.modelcron.document.Tag

interface TagService {

    fun createTag(tag: Tag): String

    fun createTag(tags: List<Tag>): String
}
