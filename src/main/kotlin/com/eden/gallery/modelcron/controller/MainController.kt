package com.eden.gallery.modelcron.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {

    @GetMapping("/")
    fun hello(): String {
        return "Hello world!"
    }
}
