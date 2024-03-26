package com.example.quizapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment

class QuizCompletionFragment(private val passed: Boolean, private val parent: AppCompatActivity) :
    DialogFragment() {
    @SuppressLint("SetTextI18n")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_quiz_completion, null)

        val msgTxtView = view.findViewById<TextView>(R.id.textView3)
        val continueBtn = view.findViewById<Button>(R.id.button)

        val builder = AlertDialog.Builder(requireContext()).setView(view)
        msgTxtView.text =
            if (passed) {
                "Συγχαρητήρια! Είχες αρκετές σωστές απαντήσεις για να περάσεις στο επόμενο κεφάλαιο."
            } else {
                ("Ουπς! Καλή προσπάθεια, όμως δεν είχες αρκετές σωστές απαντήσεις. " +
                    "Προσπάθησε ξανά, διαβάζοντας την θεωρία άλλη μία φορά.")
            }

        continueBtn.setOnClickListener {
            dismiss()

            val intent = Intent(context, TheorySelectionActivity::class.java)
            startActivity(intent)
        }

        return builder.create()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Log.i("TAG", "over")
        parent.finish()
    }
}
