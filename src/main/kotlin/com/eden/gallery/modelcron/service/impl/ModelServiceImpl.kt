package com.eden.gallery.modelcron.service.impl

import com.eden.gallery.modelcron.document.Model
import com.eden.gallery.modelcron.repository.ModelRepository
import com.eden.gallery.modelcron.service.ModelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Service implementation for model.
 */
@Service
class ModelServiceImpl(
    @Autowired val modelRepository: ModelRepository
) : ModelService {

    /**
     * Find all models by paging.
     */
    override fun findAll(page: Int, size: Int): Page<Model> {

        val pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id")
        return modelRepository.findAll(pageable)
    }

    /**
     * Save a single model.
     */
    @Transactional(readOnly = false)
    override fun save(model: Model): Boolean {

        modelRepository.save(model)
        return true
    }

    /**
     * Save multiple models.
     */
    @Transactional(readOnly = true)
    override fun saveAll(models: List<Model>): Int {

        return modelRepository.saveAll(models).size
    }

    /**
     * Find a single model that need crawling.
     */
    override fun findModelForCrawling(): Model? {

        return modelRepository.findFirstByNeedCrawlIsTrue()
    }
}
