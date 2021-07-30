package com.hrithik.kotlinroom.room

import androidx.room.*
import com.hrithik.kotlinroom.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    /**
     * To observe data changes you will use Flow<> from kotlinx-coroutines.
     * Use a return value of type Flow in your method description,
     * and Room generates all necessary code to update the Flow when the database is updated
     *
     * When Room queries return Flow, the queries are automatically run asynchronously on a background thread.
     */
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteWord(word: Word)
}