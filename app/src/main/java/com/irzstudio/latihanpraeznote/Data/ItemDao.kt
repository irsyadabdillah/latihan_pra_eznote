package com.irzstudio.latihanpraeznote.Data

import androidx.room.*

@Dao
interface ItemDao {
    @Query("SELECT * From item_table")
    fun getAll():List<Item>

    @Insert //(onConflict = REPLACE), agar id tidak ad yg sama ketika tanpa autogenerate
    fun insert(item: Item)

    @Delete
    fun delete(item: Item)

    @Update
    fun update(item: Item)

    @Query("DELETE From item_table")
    fun deleteAll()


}