package com.antiglukapps.monikaapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//You can also check out type converters
@Database(entities = [StorageItem::class], version = 1, exportSchema = false)
abstract class MonicaDatabase : RoomDatabase() {

    /**
     * This is an abstract method that returns a dao for the Db
     * */
    abstract fun getStorageItemDao(): StorageItemDao

    /**
     * A singleton design pattern is used to ensure that the database instance created is one
     * */
    companion object {
        val databaseName = "monicadb"
        var monicaDatabase: MonicaDatabase? = null

        fun getInstance(context: Context): MonicaDatabase {
            if (monicaDatabase == null) {
                monicaDatabase = Room.databaseBuilder(
                    context,
                    MonicaDatabase::class.java,
                    MonicaDatabase.databaseName
                )
                    .allowMainThreadQueries()//i will remove this later, database are not supposed to be called on main thread
                    .build()
            }
            return monicaDatabase!!
        }
    }
}
