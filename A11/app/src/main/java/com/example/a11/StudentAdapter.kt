package com.example.a11

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
    private val students: MutableList<Student>,
    private val onStudentClick: (Student, Int) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvMssv: TextView = itemView.findViewById(R.id.tvMssv)

        fun bind(student: Student, position: Int) {
            tvName.text = student.name
            tvMssv.text = student.mssv
            itemView.setOnClickListener { onStudentClick(student, position) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position], position)
    }

    override fun getItemCount(): Int = students.size

    fun updateData(newStudents: List<Student>) {
        students.clear()
        students.addAll(newStudents)
        notifyDataSetChanged()
    }
}
