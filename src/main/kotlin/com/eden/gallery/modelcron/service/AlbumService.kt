package com.eden.gallery.modelcron.service

import com.eden.gallery.modelcron.document.Album

interface AlbumService {

    fun createAlbum(request: Album) : String
    fun createAlbum(request: List<Album>) : String
}
