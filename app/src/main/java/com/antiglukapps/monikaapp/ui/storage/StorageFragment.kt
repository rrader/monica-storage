package com.antiglukapps.monikaapp.ui.storage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.antiglukapps.monikaapp.AddStorageItemActivity
import com.antiglukapps.monikaapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

//@Serial
//data class Task(var description: String, var completed: Boolean = false)

class StorageFragment : Fragment() {

    private lateinit var storageViewModel: StorageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        storageViewModel =
            ViewModelProvider(this).get(StorageViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_storage, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        storageViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        val add_button: FloatingActionButton = root.findViewById(R.id.floatingStorageAddButton)
        add_button.setOnClickListener {
            startActivity(
                Intent(
                    activity, AddStorageItemActivity::class.java
                )
            )
        }
        return root
    }
}
