package com.eden.gallery.modelcron.service

import com.eden.gallery.modelcron.document.Config

interface ConfigService {

    fun findConfig(key: String): Config?
    fun saveConfig(key: String, value: String): Boolean
}
