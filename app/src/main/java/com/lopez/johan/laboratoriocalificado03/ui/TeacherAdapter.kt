package com.lopez.johan.laboratoriocalificado03.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lopez.johan.laboratoriocalificado03.R
import com.lopez.johan.laboratoriocalificado03.model.Teacher

class TeacherAdapter(
    private val context: Context,
    private val teachers: List<Teacher>
) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    // ViewHolder para cada ítem
    class TeacherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgPhoto: ImageView = view.findViewById(R.id.imgTeacherPhoto)
        val txtName: TextView = view.findViewById(R.id.txtTeacherName)
        val txtEmail: TextView = view.findViewById(R.id.txtTeacherEmail)
    }

    // Inflar el layout de cada ítem
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_teacher, parent, false)
        return TeacherViewHolder(view)
    }

    // Asignar datos a cada ítem
    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val teacher = teachers[position]

        // Asignar foto con Glide
        Glide.with(context).load(teacher.image_url).into(holder.imgPhoto)

        // Asignar nombre y email
        holder.txtName.text = "${teacher.name} ${teacher.last_name}"
        holder.txtEmail.text = teacher.email

        // Manejar click simple (llamar)
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${teacher.phone_number}")
            context.startActivity(intent)
        }

        // Manejar click largo (enviar correo)
        holder.itemView.setOnLongClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${teacher.email}")
            }
            context.startActivity(intent)
            true
        }
    }

    // Contar el número de ítems
    override fun getItemCount(): Int = teachers.size
}