package com.eden.gallery.modelcron.service.impl

import com.eden.gallery.modelcron.document.Album
import com.eden.gallery.modelcron.repository.AlbumRepository
import com.eden.gallery.modelcron.service.AlbumService
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AlbumServiceImpl(
    @Autowired val albumRepository: AlbumRepository,
) : AlbumService, Logging {

    @Transactional(readOnly = false)
    override fun createAlbum(request: Album): String {

        val existing = albumRepository.existsByName(request.name)
        return if (!existing) {
            logger.info("creating album: ${request.name}")
            albumRepository.save(request)
            request.id.toString()
        } else {
            logger.info("already exist album: ${request.name}")
            request.name
        }
    }

    override fun createAlbum(request: List<Album>): String {

        request.stream().forEach(this::createAlbum)
        logger.info("completed")
        return "Success"
    }
}
