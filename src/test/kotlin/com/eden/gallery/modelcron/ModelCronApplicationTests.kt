package com.eden.gallery.modelcron

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@TestPropertySource(properties = ["app.scheduling.enable=false"])
class ModelCronApplicationTests {

    @Test
    fun contextLoads() {
    }
}
