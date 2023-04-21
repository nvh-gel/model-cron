package com.eden.gallery.modelcron.service.impl

import com.eden.gallery.modelcron.document.Tag
import com.eden.gallery.modelcron.repository.TagRepository
import com.eden.gallery.modelcron.service.TagService
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TagServiceImpl(
    @Autowired val tagRepository: TagRepository
) : TagService, Logging {

    @Transactional(readOnly = false)
    override fun createTag(tag: Tag): String {

        val exist : Boolean = tagRepository.existsByTag(tag.tag)
        return if (!exist) {
            logger.info("creating tag: ${tag.tag}")
            tagRepository.save(tag)
            tag.id.toString()
        } else {
            logger.info("already exist tag: ${tag.tag}")
            tag.tag
        }
    }

    override fun createTag(tags: List<Tag>): String {

        tags.stream().forEach(this::createTag)
        logger.info("completed")
        return "success"
    }
}
