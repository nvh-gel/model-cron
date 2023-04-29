package com.eden.gallery.modelcron.utils

/**
 * Tag of crawled model.
 */
@Suppress("unused")
class ModelTag(
    val name: String = "",
    val url: String = "",
    val isPublisher: Boolean = false,
    val isCategory: Boolean = false,
) {

    override fun equals(other: Any?): Boolean {
        return this === other ||
                other != null
                && other is ModelTag
                && this.name == other.name
                && this.url == other.url
    }

    override fun hashCode(): Int {

        return this.url.hashCode() * 31 + this.name.hashCode() + 31
    }
}