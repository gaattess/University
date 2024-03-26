package com.example.quizapp

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class LoginDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_login, null)

        val loginButtonFragment = view.findViewById<Button>(R.id.login_button)

        // Set up the click listener for the login button
        loginButtonFragment.setOnClickListener {
            if (onLoginAttempt(view)) {
                val intent = Intent(context, MainScreenActivity::class.java)
                context?.startActivity(intent)
                dismiss()
            } else {
                Toast.makeText(context, "Wrong email or password", Toast.LENGTH_SHORT).show()
            }
        }

        return AlertDialog.Builder(requireContext())
            .setTitle("Είσοδος χρήστη")
            .setView(view)
            .create()
    }

    private fun onLoginAttempt(view: View): Boolean {
        val emailTxt = view.findViewById<EditText>(R.id.login_email).text.toString()
        val passwordTxt = view.findViewById<EditText>(R.id.login_password).text.toString()

        val userDao = AppDatabase.getDatabase(requireContext()).userDao()

        userDao.getByEmail(emailTxt)?.run {
            if (passwordHash == passwordTxt) {
                LoggedInUserId.userId = id
                return true
            } else {
                return false
            }
        }
            ?: let {
                return false
            }
    }
}
