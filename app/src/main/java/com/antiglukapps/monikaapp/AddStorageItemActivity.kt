package com.antiglukapps.monikaapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.antiglukapps.monikaapp.data.StorageItem
import com.antiglukapps.monikaapp.data.MonicaDatabase
import java.text.SimpleDateFormat
import java.util.*


class AddStorageItemActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextExpirationDate: EditText
    private lateinit var monicaDatabase: MonicaDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_storage_item)

        monicaDatabase = MonicaDatabase.getInstance(this)

        editTextName = findViewById(R.id.editTextTextStorageItemName)
        editTextExpirationDate = findViewById(R.id.editTextExpirationDate)

        val myFormat = "MM/dd/yy"  // In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        editTextExpirationDate.setOnClickListener {
            val c = Calendar.getInstance()
            val thisYear = c.get(Calendar.YEAR)
            val thisMonth = c.get(Calendar.MONTH)
            val thisDay = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    editTextExpirationDate.setText(sdf.format(Date(year, monthOfYear, dayOfMonth)))
                },
                thisYear,
                thisMonth,
                thisDay
            )

            dpd.show()
        }

        val confrmButton : Button = findViewById(R.id.buttonAddItemConfirm)
        confrmButton.setOnClickListener {
            val expires : Date = sdf.parse(editTextExpirationDate.text.toString())!!
            val storageItem = StorageItem(
                editTextName.text.toString(),
                expires.time
            )
            monicaDatabase.getStorageItemDao().saveStorageItem(storageItem)
            finish()
        }
    }
}
