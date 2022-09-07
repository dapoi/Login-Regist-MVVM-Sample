package com.dapascript.assignmentscout.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dapascript.assignmentscout.databinding.ActivityMainBinding
import com.dapascript.assignmentscout.util.Resource
import com.dapascript.assignmentscout.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        binding.rvData.apply {
            userAdapter = UserAdapter()
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
            setHasFixedSize(true)
        }
    }

    private fun initViewModel() {
        userViewModel.getUser().observe(this) { result ->
            when (result) {
                is Resource.Loading -> true.loading()
                is Resource.Success -> {
                    false.loading()
                    userAdapter.setList(result.data!!)
                }
                is Resource.Error -> {
                    false.loading()
                    Toast.makeText(this, "Data not found", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun Boolean.loading() {
        binding.pbData.apply {
            visibility = if (this@loading) {
                VISIBLE
            } else {
                GONE
            }
        }
    }


}