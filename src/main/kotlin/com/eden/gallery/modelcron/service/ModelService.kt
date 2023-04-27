package com.eden.gallery.modelcron.service

import com.eden.gallery.modelcron.document.Model
import org.springframework.data.domain.Page

/**
 * Service for model handling.
 */
interface ModelService {

    /**
     * Find all models by paging.
     */
    fun findAll(page: Int, size: Int): Page<Model>

    /**
     * Save a single model.
     */
    fun save(model: Model): Boolean

    /**
     * Save multiple models.
     */
    fun saveAll(models: List<Model>): Int

    /**
     * Find a single model that need crawling.
     */
    fun findModelForCrawling(): Model?
}
