package com.eden.gallery.modelcron

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ModelCronApplication

fun main(args: Array<String>) {
    runApplication<ModelCronApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}
