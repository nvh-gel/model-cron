package com.eden.gallery.modelcron.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Config data.
 */
@Suppress("unused")
@Document
class Config(
    @Id val key: String,
    val value: String = "",
)
