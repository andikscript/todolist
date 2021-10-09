package com.andik.uasmobiledua.model

import com.google.gson.Gson

class Todo {
    var id = 0
    var name = ""

    constructor()
    constructor(name: String) {
        this.name = name
    }

    fun toJson(): String {
        return Gson().toJson(this, Todo::class.java)
    }

    fun fromJson(json: String): Todo {
        if (json.isEmpty()) return Todo()
        return Gson().fromJson(json, Todo::class.java)
    }
}