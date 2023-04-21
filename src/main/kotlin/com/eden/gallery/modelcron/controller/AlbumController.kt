package com.eden.gallery.modelcron.controller

import com.eden.gallery.modelcron.document.Album
import com.eden.gallery.modelcron.service.AlbumService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/album")
class AlbumController(
    @Autowired val albumService: AlbumService
) {

    @PostMapping
    fun createAlbum(@RequestBody album: Album): String {

        return albumService.createAlbum(album)
    }
}
