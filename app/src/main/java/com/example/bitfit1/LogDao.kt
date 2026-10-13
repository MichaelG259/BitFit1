package com.example.bitfit1

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import kotlinx.coroutines.flow.Flow

@Dao
interface LogDao {
    @Query("SELECT * FROM log_table")
    fun getAll(): Flow<List<LogEntity>>

    @Insert
    fun insert(logs: LogEntity)

    @Query("DELETE FROM log_table")
    fun deleteAll()
}