package com.eden.gallery.modelcron.service

import com.eden.gallery.modelcron.document.Config

/**
 * Service for config handling.
 */
interface ConfigService {

    /**
     * Find a single config by key.
     */
    fun findConfig(key: String): Config?

    /**
     * Save a config, create if not exists.
     */
    fun saveConfig(key: String, value: String): Boolean
}
