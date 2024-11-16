package com.lopez.johan.laboratoriocalificado03.network

import com.lopez.johan.laboratoriocalificado03.model.Teacher
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("list/teacher-b")
    fun getTeachers(): Call<Map<String, List<Teacher>>>
}