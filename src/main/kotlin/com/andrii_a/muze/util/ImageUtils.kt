package com.andrii_a.muze.util

import java.awt.image.BufferedImage

val BufferedImage.resolution: Pair<Int, Int>
    get() = Pair(width, height)