package com.github.zorenkonte

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.http.HttpStatus
import java.util.concurrent.atomic.AtomicInteger

fun main() {
    val app = Javalin.create().apply {
        exception(Exception::class.java) { e, _ -> e.printStackTrace() }
        error(HttpStatus.NOT_FOUND) { it.json("not found") }
            .start()
    }

    val userDao = UserDao()

    app.routes {
        get("/users") {
            it.json(userDao.users)
        }

        get("/users/{id}") {
            userDao.findById(it.pathParam("id").toInt())?.let { it1 -> it.json(it1) }
        }
    }
}

data class User(val name: String, val email: String, val id: Int)

class UserDao {

    // "Initialize" with a few users
    // This demonstrates type inference, map-literals and named parameters
    val users = hashMapOf(
        0 to User(name = "Alice", email = "alice@alice.kt", id = 0),
        1 to User(name = "Bob", email = "bob@bob.kt", id = 1),
        2 to User(name = "Carol", email = "carol@carol.kt", id = 2),
        3 to User(name = "Dave", email = "dave@dave.kt", id = 3)
    )

    var lastId: AtomicInteger = AtomicInteger(users.size - 1)

    fun save(name: String, email: String) {
        val id = lastId.incrementAndGet()
        users[id] = User(name = name, email = email, id = id)
    }

    fun findById(id: Int): User? {
        return users[id]
    }

    fun findByEmail(email: String): User? {
        return users.values.find { it.email == email } // == is equivalent to java .equals() (referential equality is checked by ===)
    }

    fun update(id: Int, user: User) {
        users[id] = User(name = user.name, email = user.email, id = id)
    }

    fun delete(id: Int) {
        users.remove(id)
    }
}