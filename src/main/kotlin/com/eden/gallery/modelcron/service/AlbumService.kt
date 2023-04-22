package com.eden.gallery.modelcron.service

import com.eden.gallery.modelcron.document.Album

/**
 * Service for album handling.
 */
interface AlbumService {

    /**
     * Create an album from request.
     */
    fun createAlbum(album: Album) : Boolean

    /**
     * Create multiple albums from list.
     */
    fun createAlbum(albums: List<Album>) : Boolean
}
