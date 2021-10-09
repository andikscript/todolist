package com.andik.uasmobiledua.ui.activity.todo

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.andik.uasmobiledua.R
import com.andik.uasmobiledua.app.ApiConfig
import com.andik.uasmobiledua.model.Todo
import com.andik.uasmobiledua.model.ResponModel
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_tambah_todo.*
import retrofit2.Callback
import retrofit2.Response

class TambahTodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_todo)
        btn_simpan.setOnClickListener {
            if (edt_list.text.toString().isEmpty()) {
                edt_list.error = "Kolom Todo List tidak boleh kosong"
                edt_list.requestFocus()
            } else {
                val todo = Todo()
                todo.name = edt_list.text.toString()
                createTodo(todo)
            }
        }
    }

    private fun createTodo(todo: Todo) {
        val loading = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        loading.setTitleText("Loading....").show()

        ApiConfig.instanceRetrofit.createTodo(todo).enqueue(object : Callback<ResponModel> {
            override fun onFailure(call: retrofit2.Call<ResponModel>, t: Throwable) {
                loading.dismiss()
                Log.d(ContentValues.TAG, "onFailure")
            }

            override fun onResponse(call: retrofit2.Call<ResponModel>, response: Response<ResponModel>) {
                loading.dismiss()
                if (response.isSuccessful) {
                    val res = response.body()!!
                    if(res.success == 1) {
                        toast("List berhasil dibuat")
                        onBackPressed()
                    } else toast(res.message )
                } else toast(response.message())
            }
        })
    }

    private fun toast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }
}