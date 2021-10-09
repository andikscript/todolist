package com.andik.uasmobiledua.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andik.uasmobiledua.R
import com.andik.uasmobiledua.model.Todo

class AdapterTodo (var data: ArrayList<Todo>):
RecyclerView.Adapter<AdapterTodo.Holder>() {

    class Holder(view: View): RecyclerView.ViewHolder(view) {
        val tvList = view.findViewById<TextView>(R.id.tv_list)
        val layout = view.findViewById<LinearLayout>(R.id.layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View =
                LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val a = data[position]

        holder.tvList.text = a.name
        holder.layout.setOnClickListener {

        }
    }
}