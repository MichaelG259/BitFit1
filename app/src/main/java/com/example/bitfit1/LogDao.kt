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

    @Query("SELECT MAX(hours) FROM log_table")
    fun getMax(): Double?

    @Query("SELECT MIN(hours) FROM log_table")
    fun getMin(): Double?

    @Query("SELECT AVG(hours) FROM log_table")
    fun getAvg(): Double?
}