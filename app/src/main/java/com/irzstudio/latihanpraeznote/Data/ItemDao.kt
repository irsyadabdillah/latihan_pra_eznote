package com.irzstudio.latihanpraeznote.Data

import androidx.room.*

@Dao
interface ItemDao {
    @Query("SELECT * From item")
    fun getAll():List<Item>

    @Insert //(onConflict = REPLACE), agar id tidak ad yg sama ketika tanpa autogenerate
    fun insert(note: Item)

    @Delete
    fun delete(note: Item)

    @Update
    fun update(note: Item)

}