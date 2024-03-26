package com.example.quizapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class FAQActivity : AppCompatActivity() {

    val faqList = ArrayList<FAQs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.faq_recyclerview)
        val FaqList = getFAQ()

        val faqAdapter = FAQAdapter(faqList, this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            adapter = faqAdapter
        }
    }

    private fun getFAQ() {
        faqList.add(
            FAQs(
                "Μπορώ να χρησιμοποιήσω την εφαρμογή αν δεν είμαι μαθητής Β' Γυμνασίου και είμαι μεγαλύτερος;",
                "Φυσικά και μπορείς! Ένας από τους στόχους της εφαρμογής είναι η επανάληψη της ύλης σε περίπτωση έλλειψης γνώσεων."
            )
        )

        faqList.add(
            FAQs(
                "Μπορώ να χρησιμοποιήσω την εφαρμογή αν δεν είμαι μαθητής Β' Γυμνασίου και είμαι μικρότερος;"
                , "Θα ήταν προτιμότερο η εφαρμογή να χρησιμοποιηθεί όταν υπάρχουν οι προαπαιτούμενες μαθηματικές βάσεις, για την καλύτερη κατανόηση της ύλης."
            )
        )

        faqList.add(
            FAQs(
                "Τι ερωτήσεις περιλαμβάνει το quiz και πόσες είναι;",
                "Το quiz περιλαμβάνει 8 ενότητες, οι οποίες αντιστοιχούν στα πρώτα 2 κεφάλαια του βιβλίου των μαθηματικών Β' Γυμνασίου." +
                        " Το κάθε κεφάλαιο περιλαμβάνει 10 ερωτήσεις (εκτός από το τελευταίο)."
            )
        )

        faqList.add(
            FAQs(
                "Μπορώ να ξαναπροσπαθήσω κάποιο quiz;",
                "Δίνονται όσες ευκαιρίες χρειάζεται κάποιος για να ολοκληρωθεί ένα κεφάλαιο. Όταν ολοκληρωθούν όλα τα quiz, τότε μόνο γίνεται να επαναληφθεί κάποιο."
            )
        )

        faqList.add(
            FAQs(
                "Με ποια σειρά μπορώ να ξεκινήσω τα quiz;",
                "Τα quiz θα πρέπει να ξεκινάνε από το πρώτο κεφάλαιο, και με κάθε επιτυχής συμπλήρωση θα ξεκλειδώνεται το επόμενο."
            )
        )

        faqList.add(
            FAQs(
                "Πότε ένα quiz θεωρείται επιτυχές;",
                "Όταν οι σωστές απαντήσεις είναι από 8 και πάνω."
            )
        )

        faqList.add(
            FAQs(
                "Που μπορώ να δω πως τα έχω πάει στα ολοκληρωμένα quiz;",
                "Πατώντας το κουμπί 'Στατιστικά' εντός της αρχικής οθόνης της εφαρμογής."
            )
        )

        faqList.add(
            FAQs(
                "Γιατί η θεωρία έχει 2 κεφάλαια παραπάνω από τα quiz;",
                "Η θεωρία περιέχει 2 επαναληπτικά κεφάλαια, τα οποία καλύπτουν τα κεφάλαια 1, 2, 3, 4 και τα 6, 7, 8 αντίστοιχα. "
            )
        )

    }
}

data class FAQs(val codeName: String, val description: String, var expandable: Boolean = false)