package com.example.notesfinal.zbsearch.ui.ratings

import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.notesfinal.zbsearch.R
import com.example.notesfinal.zbsearch.databinding.RatingsFragmentBinding
import com.example.notesfinal.zbsearch.utils.viewBinding


class RatingsFragment : Fragment(R.layout.ratings_fragment) {

    companion object {
        fun newInstance() = RatingsFragment()
    }

    private val viewModel: RatingsViewModel by viewModels()

    private val binding: RatingsFragmentBinding by viewBinding(RatingsFragmentBinding::bind)

    private val adapter = ContactsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val contentResolver = requireContext().contentResolver

        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.Contacts.DISPLAY_NAME + " DESC"
        )

        val contacts = mutableListOf<Contact>()
        val safeCursor = cursor ?: return

        while (cursor.moveToNext()) {
            contacts.add(
                Contact(
                    safeCursor.getString(safeCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA)),
                    safeCursor.getString(safeCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                )
            )
        }

        cursor.close()

        adapter.list = contacts
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainNowPlayingRecyclerView.adapter = adapter
    }
}