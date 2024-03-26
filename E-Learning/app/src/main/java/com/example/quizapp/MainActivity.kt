package com.example.quizapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private val loginDialogTag = "login_dialog"
    private val signupDialogTag = "signup_dialog"
    private var backPressedTime: Long = 0 // Used to track the time when back button is pressed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentManager = supportFragmentManager

        findViewById<Button>(R.id.loginButton).setOnClickListener { showLoginDialog() }

        findViewById<Button>(R.id.signUpButton).setOnClickListener { showSignupDialog() }
    }

    private fun showLoginDialog() {
        val loginDialog = LoginDialogFragment()
        loginDialog.show(fragmentManager, loginDialogTag)
    }

    private fun showSignupDialog() {
        val signupDialog = SignupDialogFragment()
        signupDialog.show(fragmentManager, signupDialogTag)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - backPressedTime > 2000) {
            // Show a toast message to inform the user to press back again to exit
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
            backPressedTime = currentTime
        } else {
            // If the back button is pressed again within 2 seconds, exit the app
            finishAffinity()
        }
    }
}
