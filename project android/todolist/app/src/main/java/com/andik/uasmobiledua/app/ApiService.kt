package com.andik.uasmobiledua.app

import com.andik.uasmobiledua.model.Todo
import com.andik.uasmobiledua.model.ResponModel
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("todo")
    fun getTodo(): Call<ResponModel>

    @POST("todo")
    fun createTodo(
        @Body data: Todo
    ): Call<ResponModel>

    @POST("todo/{id}")
    fun updateTodo(
        @Path ("id") id: Int,
        @Body data: Todo
    ): Call<ResponModel>

    @DELETE("todo/{id}")
    fun deleteTodo(
        @Path ("id") id: Int,
    ): Call<ResponModel>
}