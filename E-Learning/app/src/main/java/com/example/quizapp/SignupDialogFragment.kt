package com.example.quizapp

import android.app.Dialog
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.quizapp.entities.UserEntity

class SignupDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_signup, null)

        val signUpBtn = view.findViewById<Button>(R.id.signup_button)
        signUpBtn.setOnClickListener { onSignUpAttempt(view) }

        return AlertDialog.Builder(requireContext())
            .setTitle("Εγγραφή νέου χρήστη")
            .setView(view)
            .create()
    }

    private fun onSignUpAttempt(view: View) {
        val emailTxt = view.findViewById<EditText>(R.id.signup_email)
        val usernameTxt = view.findViewById<EditText>(R.id.signup_username)
        val passwordTxt = view.findViewById<EditText>(R.id.signup_password)
        val confirmPasswordTxt = view.findViewById<EditText>(R.id.signup_confirm)

        if (!assertValid(emailTxt, usernameTxt, passwordTxt, confirmPasswordTxt)) return

        val userDao = AppDatabase.getDatabase(requireContext()).userDao()
        val userEntity =
            UserEntity(
                emailTxt.text.toString(),
                usernameTxt.text.toString(),
                passwordTxt.text.toString()
            )

        try {
            userDao.insertOne(userEntity)

            Log.i("TAG", "Singed up: ${emailTxt.text}")
            dismiss()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(context, "Αυτό το Email χρησιμοποιείτε ήδη", Toast.LENGTH_SHORT).show()
        }
    }

    private fun assertValid(
        emailTxt: EditText,
        usernameTxt: EditText,
        passwordTxt: EditText,
        confirmPasswordTxt: EditText
    ): Boolean {
        if (emailTxt.text.isBlank()) {
            Toast.makeText(context, "You need a valid email address", Toast.LENGTH_SHORT).show()
            return false
        }
        if (usernameTxt.text.isBlank()) {
            Toast.makeText(context, "Username cannot be empty", Toast.LENGTH_SHORT).show()
            return false
        }
        if (passwordTxt.text.isBlank()) {
            Toast.makeText(context, "Password cannot be empty", Toast.LENGTH_SHORT).show()
            return false
        }
        if (confirmPasswordTxt.text.isBlank()) {
            Toast.makeText(context, "Please confirm your password", Toast.LENGTH_SHORT).show()
            return false
        }
        if (confirmPasswordTxt.text.equals(passwordTxt.text)) {
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}
