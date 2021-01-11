package com.antiglukapps.monikaapp.ui.storageItemEdit

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.antiglukapps.monikaapp.R
import com.antiglukapps.monikaapp.data.MonicaDatabase
import com.antiglukapps.monikaapp.data.StorageItem
import com.antiglukapps.monikaapp.helpers.ExpirationHelpers
import java.util.*


class AddStorageItemActivity : AppCompatActivity() {

    private lateinit var addStorageItemViewModel: AddStorageItemViewModel

    private lateinit var editTextName: EditText
    private lateinit var editTextExpirationDate: EditText
    private lateinit var monicaDatabase: MonicaDatabase

    private var editStorageItemId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_storage_item)

        addStorageItemViewModel = ViewModelProvider(this).get(AddStorageItemViewModel::class.java)

        monicaDatabase = MonicaDatabase.getInstance(this)

        editTextName = findViewById(R.id.editTextTextStorageItemName)
        addStorageItemViewModel.name.observe(this, Observer {
            editTextName.setText(it)
        })
        editTextName.doAfterTextChanged {
            if (addStorageItemViewModel.name.value != it.toString()) {
                addStorageItemViewModel.name.value = it.toString()
            }
        }

        editTextExpirationDate = findViewById(R.id.editTextExpirationDate)
        addStorageItemViewModel.expirationDate.observe(this, Observer {
            editTextExpirationDate.setText(ExpirationHelpers.format(it))
        })

        val b = this.intent.extras
        if (b != null) {
            editStorageItemId = b.getInt("storageItemId")
            val editStorageItem =
                monicaDatabase.getStorageItemDao().getStorageItemItem(editStorageItemId as Int)
            addStorageItemViewModel.name.value = editStorageItem.title
            addStorageItemViewModel.expirationDate.value = editStorageItem.expiration_dt
        }

        editTextExpirationDate.setOnClickListener {
            var c = Calendar.getInstance()
            val editTime = addStorageItemViewModel.expirationDate.value as Long
            if (editTime != 0.toLong()) {
                c = ExpirationHelpers.parse(editTime)
            }

            val dpd = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    addStorageItemViewModel.expirationDate.value =
                        ExpirationHelpers.toLong(year - 1900, monthOfYear, dayOfMonth)
                },
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            )

            dpd.show()
        }

        val confrmButton: Button = findViewById(R.id.buttonAddItemConfirm)
        confrmButton.setOnClickListener {
            var tId = 0
            if (editStorageItemId != null) {
                tId = editStorageItemId as Int
            }

            val storageItem = StorageItem(
                addStorageItemViewModel.name.value as String,
                addStorageItemViewModel.expirationDate.value as Long,
                tId
            )

            if (editStorageItemId != null) {
                monicaDatabase.getStorageItemDao().updateStorageItem(storageItem)
            } else {
                monicaDatabase.getStorageItemDao().saveStorageItem(storageItem)
            }
            finish()
        }
    }
}
