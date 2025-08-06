package com.countdown.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.countdown.data.entity.CountdownEntity

@Dao
interface CountdownDao {

    @Query("SELECT * FROM countdown")
    fun getAll(): List<CountdownEntity>

    @Insert
    fun insert(countdown: CountdownEntity)

    @Delete
    fun delete(countdown: CountdownEntity)

    @Update
    fun update(countdown: CountdownEntity)


}