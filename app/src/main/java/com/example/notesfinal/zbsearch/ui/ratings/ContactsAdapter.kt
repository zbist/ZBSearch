package com.example.notesfinal.zbsearch.ui.ratings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesfinal.zbsearch.R

class ContactsAdapter : RecyclerView.Adapter<ContactsAdapter.ContactHolder>() {

    var list = emptyList<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_item, parent, false)
        return ContactHolder(view)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        with(holder) {
            nameOfContact.text = list[position].name
            phoneOfContact.text = list[position].phone
        }
    }

    override fun getItemCount(): Int = list.size

    inner class ContactHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameOfContact = itemView.findViewById<TextView>(R.id.name_of_contact)
        val phoneOfContact = itemView.findViewById<TextView>(R.id.phone_of_contact)
    }
}

data class Contact(
    val name: String,
    val phone: String,
)