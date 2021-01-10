package com.antiglukapps.monikaapp.data

import androidx.room.*

@Entity(tableName = "storage_item")
data class StorageItem(
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "expiration") val expiration_dt: Long = 0,
    @PrimaryKey(autoGenerate = true) val tId: Int = 0
)


@Dao
interface StorageItemDao {

    /**
     * SELECT -> This retrieve rows from a table in a database
     * FROM -> You specify the table to retrieve the rows from
     * ORDER BY -> This is just a sort algorithm
     * ASC -> Ascending order
     * WHERE -> This is a condition used to query data
     * */
    @Query("SELECT*FROM storage_item ORDER BY tId ASC")
    fun getStorageItemList(): List<StorageItem>


    @Query("SELECT*FROM storage_item WHERE tId=:tid")
    fun getStorageItemItem(tid: Int): StorageItem

    /**
     * @param StorageItem is what we want to save in our database
     * so many conflict can occur when a data is to be saved, the strategy is used to handle such conflicts
     * Abort -> this aborts the transaction
     * Ignore -> this ignores and continues the transaction
     * Replace -> this replace the data
     * others includes fail, and roolback
     * */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveStorageItem(storageItem: StorageItem)

    @Update
    fun updateStorageItem(storageItem: StorageItem)

    @Delete
    fun removeStorageItem(storageItem: StorageItem)
}
