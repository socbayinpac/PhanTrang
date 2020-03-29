package com.example.phantrang.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phantrang.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

  val viewModel: MainActivityViewModel by viewModel<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = NumbersAdapter()
        recyclerview.adapter = adapter
        viewModel.numberList.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.isLoading.observe(this, Observer {
            refresh.isRefreshing = it
        })


        refresh.setOnRefreshListener {
            viewModel.refresh()
        }

    }


}
