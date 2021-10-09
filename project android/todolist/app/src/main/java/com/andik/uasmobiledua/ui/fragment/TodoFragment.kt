package com.andik.uasmobiledua.ui.fragment

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andik.uasmobiledua.R
import com.andik.uasmobiledua.adapter.AdapterTodo
import com.andik.uasmobiledua.app.ApiConfig
import com.andik.uasmobiledua.model.Todo
import com.andik.uasmobiledua.model.ResponModel
import com.andik.uasmobiledua.ui.activity.todo.TambahTodoActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoFragment : Fragment() {

    lateinit var btnTambahList : Button
    lateinit var tvStatus : TextView
    lateinit var rvList : RecyclerView
    lateinit var pd : ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_todo, container, false)
        init(root)
        mainButton()
        return root
    }

    private fun mainButton() {
        btnTambahList.setOnClickListener {
            startActivity(Intent(requireActivity(), TambahTodoActivity::class.java))
        }
    }

    private fun loadData() {
        pd.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.getTodo().enqueue(object : Callback<ResponModel>{
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                pd.visibility = View.GONE
                Log.d(ContentValues.TAG, "onFailure")
            }

            override fun onResponse(call: retrofit2.Call<ResponModel>, response: Response<ResponModel>) {
                pd.visibility = View.GONE
                if (response.isSuccessful) {
                    val res = response.body()!!
                    if (res.success==1) displayTodo(res.todoses)
                    else toast(res.message)
                } else toast(response.message())
            }
        })
    }

    private fun displayTodo(listTodo: ArrayList<Todo>) {
        if (listTodo.isEmpty()) {
            tvStatus.visibility = View.VISIBLE
        } else {
            tvStatus.visibility = View.GONE
        }

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rvList.adapter = AdapterTodo(listTodo)
        rvList.layoutManager = layoutManager
    }

    fun toast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    private fun init(view: View) {
        btnTambahList = view.findViewById(R.id.btn_tambahtodo)
        tvStatus = view.findViewById(R.id.tv_status)
        rvList = view.findViewById(R.id.rv_todo)
        pd = view.findViewById(R.id.pd)
    }

    override fun onResume() {
        loadData()
        super.onResume()
    }
}