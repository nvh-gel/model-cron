package com.eden.gallery.modelcron.service.impl

import com.eden.gallery.modelcron.document.Config
import com.eden.gallery.modelcron.repository.ConfigRepository
import com.eden.gallery.modelcron.service.ConfigService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Implementation for config service.
 */
@Service
class ConfigServiceImpl(
    @Autowired val configRepository: ConfigRepository
) : ConfigService {

    /**
     * Find a single config by key.
     */
    override fun findConfig(key: String): Config? {

        return configRepository.findById(key).orElse(null)
    }

    /**
     * Save a config, create if not exists.
     */
    @Transactional(readOnly = false)
    override fun saveConfig(key: String, value: String): Boolean {

        val config = Config(key, value)
        configRepository.save(config)
        return true
    }
}
