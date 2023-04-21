package com.eden.gallery.modelcron.service.impl

import com.eden.gallery.modelcron.document.Config
import com.eden.gallery.modelcron.repository.ConfigRepository
import com.eden.gallery.modelcron.service.ConfigService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ConfigServiceImpl(
    @Autowired val configRepository: ConfigRepository
) : ConfigService {

    override fun findConfig(key: String): Config? {

        return configRepository.findById(key).orElse(null)
    }

    @Transactional(readOnly = false)
    override fun saveConfig(key: String, value: String): Boolean {

        val config = Config(key, value)
        configRepository.save(config)
        return true
    }
}
