package com.eden.gallery.modelcron.repository

import com.eden.gallery.modelcron.document.Config
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * Repository for configs.
 */
@Repository
interface ConfigRepository: MongoRepository<Config, String>
