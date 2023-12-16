package com.miu.cs473.foodiepal

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("foodiePalPreference", Context.MODE_PRIVATE)

        // Check if the user is already logged in
        if (isLoggedIn()) {
            navigateToMainActivity()
        }

        setClickListeners()
    }

    private fun setClickListeners() {
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Perform basic validation
            if (isValidCredentials(username, password)) {
                // Save the logged-in state in SharedPreferences
                saveLoginState(username)

                // Navigate to the main activity
                navigateToMainActivity()
            } else {
                showToast("Invalid credentials")
            }
        }
    }

    private fun initViews() {
        // Initialize UI components
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
    }

    private fun isLoggedIn(): Boolean {
        // Check if the user is already logged in
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        return username == "demo" && password == "password"
    }

    private fun saveLoginState(username: String) {
        // Save the logged-in state in SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", true)
        editor.putString("username", username)
        editor.apply()
    }

    private fun navigateToMainActivity() {
        // Launch the main activity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}