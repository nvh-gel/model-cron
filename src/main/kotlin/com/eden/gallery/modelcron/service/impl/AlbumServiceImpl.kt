package com.eden.gallery.modelcron.service.impl

import com.eden.gallery.modelcron.document.Album
import com.eden.gallery.modelcron.repository.AlbumRepository
import com.eden.gallery.modelcron.service.AlbumService
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Implementation for album service.
 */
@Service
class AlbumServiceImpl(
    @Autowired val albumRepository: AlbumRepository,
) : AlbumService, Logging {

    /**
     * Create an album from request data.
     */
    @Transactional(readOnly = false)
    override fun createAlbum(album: Album): Boolean {

        val existing = albumRepository.existsByName(album.name)
        return if (!existing) {
            val created = albumRepository.save(album)
            logger.info("created album: ${created.name}")
            true
        } else {
            logger.info("already exist album: ${album.name}")
            false
        }
    }

    /**
     * Create albums from list of data.
     */
    @Transactional(readOnly = false)
    override fun createAlbum(albums: List<Album>): Boolean {

        val names = albums.map(Album::name).toList()
        val existing = albumRepository.findAllByNameIn(names).map(Album::name).toList()
        logger.info("already exist: $existing")

        val toCreate = albums.filter { a -> !existing.contains(a.name) }.toList()
        val created = albumRepository.saveAll(toCreate).map(Album::name).toList()
        logger.info("created ${created.size} albums: $created")

        return true
    }
}
