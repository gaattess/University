package com.example.quizapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FAQAdapter(private val faqList: List<FAQs>, private val ctx: Context) :
    RecyclerView.Adapter<FAQAdapter.FaqsViewHolder>(){
        class FaqsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var faqQuestion : TextView = itemView.findViewById(R.id.faq_question)
            var faqAnswer : TextView = itemView.findViewById(R.id.faq_answer)
            val linearLayout : LinearLayout = itemView.findViewById(R.id.linearLayout)
            var expandableLayout : RelativeLayout = itemView.findViewById(R.id.expandable_layout)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqsViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.faq_card, parent, false)

        return FaqsViewHolder(view)
    }

    override fun getItemCount() = faqList.size


    override fun onBindViewHolder(holder: FaqsViewHolder, position: Int) {
        // This method retrieves data and displays inside the view (i.e. Card) while binding
        val faqs : FAQs = faqList[position]
        holder.faqQuestion.text = faqs.codeName
        holder.faqAnswer.text = faqs.description

        val isExpandable : Boolean = faqList[position].expandable
        holder.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.linearLayout.setOnClickListener{
            val faqs = faqList[position]
            faqs.expandable = !faqs.expandable
            notifyItemChanged(position)
        }

    }
}