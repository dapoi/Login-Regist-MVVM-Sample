package com.dapascript.assignmentscout.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dapascript.assignmentscout.databinding.ActivityLoginBinding
import com.dapascript.assignmentscout.util.Resource
import com.dapascript.assignmentscout.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            when {
                email.isEmpty() -> {
                    binding.inputEmail.error = "Email is required"
                    binding.inputEmail.requestFocus()
                }
                password.isEmpty() -> {
                    binding.inputPassword.error = "Password is required"
                    binding.inputPassword.requestFocus()
                }
                else -> {
                    initViewModel(email, password)
                }
            }

            currentFocus?.let {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(it.windowToken, 0)
            }
        }
    }

    private fun initViewModel(email: String, password: String) {
        userViewModel.login(email, password).observe(this) {
            when (it) {
                is Resource.Loading -> binding.progressBar.visibility = VISIBLE
                is Resource.Success -> {
                    binding.progressBar.visibility = GONE
                    Intent(this, MainActivity::class.java).also { intent ->
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                    finish()
                    hasLogin()
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = GONE
                    Toast.makeText(this, "User not found", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun hasLogin() {
        val sharedPref = getSharedPreferences("user", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        with(editor) {
            putBoolean("hasLogin", true)
            apply()
        }
    }
}