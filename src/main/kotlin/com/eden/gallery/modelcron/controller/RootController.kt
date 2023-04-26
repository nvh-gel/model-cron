package com.eden.gallery.modelcron.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Basic controller for healthcheck.
 */
@RestController
@RequestMapping("")
class RootController {

    /**
     * Root path.
     */
    @GetMapping
    fun getRoot(): String {

        return "Hello!"
    }

    /**
     * Health check path.
     */
    @GetMapping("/healthz")
    fun getHealth(): String {

        return "UP"
    }
}
