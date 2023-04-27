package com.eden.gallery.modelcron.service.impl

import com.eden.gallery.modelcron.document.Tag
import com.eden.gallery.modelcron.repository.TagRepository
import com.eden.gallery.modelcron.service.TagService
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Implementation for tag service
 */
@Service
class TagServiceImpl(
    @Autowired val tagRepository: TagRepository,
) : TagService, Logging {

    /**
     * Create a single tag from request.
     */
    @Transactional(readOnly = false)
    override fun createTag(tag: Tag): Boolean {

        val exist: Boolean = tagRepository.existsByTag(tag.tag)
        return if (!exist) {
            tagRepository.save(tag)
            logger.info("created tag: ${tag.tag}")
            true
        } else {
            logger.info("already exist tag: ${tag.tag}")
            false
        }
    }

    /**
     * Create multiple tags from list.
     */
    @Transactional(readOnly = false)
    override fun createTag(tags: Set<Tag>): Boolean {

        val tagNames = tags.map(Tag::tag).toList()
        val existingTags = tagRepository.findAllByTagIn(tagNames).map(Tag::tag).toList()
        logger.info("existing tags: $existingTags")

        val toCreate = tags.filter { t -> !existingTags.contains(t.tag) }.toSet()
        val created = tagRepository.saveAll(toCreate).map(Tag::tag).toList()
        logger.info("created ${created.size} tags: $created")

        return true
    }

    /**
     * Update a list of tags.
     */
    @Transactional(readOnly = false)
    override fun saveAll(tags: List<Tag>): Int {

        return tagRepository.saveAll(tags).size
    }

    /**
     * Find a list of model tags by paging.
     */
    override fun findModelTags(page: Int, size: Int): List<Tag> {

        val pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id")
        return tagRepository.findAllByConvertedIsFalse(pageable).toList()
    }
}
