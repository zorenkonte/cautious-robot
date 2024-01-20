package com.github.zorenkonte

import io.javalin.Javalin

fun main() {
    val app = Javalin.create()
        .get("/") { ctx -> ctx.result("Hello World") }
        .start(7070)
}