package com.csc415.smithsona2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

data class LatinText(val id: Int, val userId: Int, val title: String, val body: String)

interface PostService
{
	@GET("/posts")
	fun getPosts(): Call<List<LatinText>>

	@GET("/posts")
	fun getPost(@Query("id") id: Int): Call<List<LatinText>>
}