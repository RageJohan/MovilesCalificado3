package com.lopez.johan.laboratoriocalificado03.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lopez.johan.laboratoriocalificado03.R
import com.lopez.johan.laboratoriocalificado03.model.Teacher
import com.lopez.johan.laboratoriocalificado03.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Ejercicio01 : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var teacherAdapter: TeacherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio01)

        recyclerView = findViewById(R.id.rvTeachers)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchTeachers()
    }

    private fun fetchTeachers() {
        RetrofitClient.apiService.getTeachers().enqueue(object : Callback<Map<String, List<Teacher>>> {
            override fun onResponse(call: Call<Map<String, List<Teacher>>>, response: Response<Map<String, List<Teacher>>>) {
                if (response.isSuccessful) {
                    val teachers = response.body()?.get("teachers") ?: emptyList()
                    teacherAdapter = TeacherAdapter(this@Ejercicio01, teachers)
                    recyclerView.adapter = teacherAdapter
                } else {
                    // Manejar el error de respuesta no exitosa
                    Log.e("Ejercicio01", "Error en la respuesta del API: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Map<String, List<Teacher>>>, t: Throwable) {
                // Manejar el error de falla en la solicitud
                Log.e("Ejercicio01", "Error en la solicitud del API: ${t.message}")
            }
        })
    }
}