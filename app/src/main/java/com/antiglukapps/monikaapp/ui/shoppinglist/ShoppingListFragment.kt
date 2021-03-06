package com.antiglukapps.monikaapp.ui.shoppinglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.antiglukapps.monikaapp.R

class ShoppingListFragment : Fragment() {

    private lateinit var shoppingListViewModel: ShoppingListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shoppingListViewModel =
            ViewModelProvider(this).get(ShoppingListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_shoppinglist, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        shoppingListViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
