package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AnesthesiaDao {

    // Bookmarks
    @Query("SELECT * FROM bookmarks ORDER BY timestamp DESC")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE id = :id)")
    fun isBookmarked(id: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkEntity)

    @Query("DELETE FROM bookmarks WHERE id = :id")
    suspend fun deleteBookmark(id: String)

    // Notes
    @Query("SELECT * FROM user_notes WHERE targetId = :targetId ORDER BY updatedAt DESC")
    fun getNotesForTarget(targetId: String): Flow<List<UserNoteEntity>>

    @Query("SELECT * FROM user_notes ORDER BY updatedAt DESC")
    fun getAllNotes(): Flow<List<UserNoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: UserNoteEntity)

    @Query("DELETE FROM user_notes WHERE id = :noteId")
    suspend fun deleteNote(noteId: Int)

    // User Preferences & Progress
    @Query("SELECT prefValue FROM user_preferences WHERE prefKey = :key LIMIT 1")
    suspend fun getPreference(key: String): String?

    @Query("SELECT * FROM user_preferences")
    fun getAllPreferences(): Flow<List<UserPreferenceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setPreference(preference: UserPreferenceEntity)

    @Query("DELETE FROM user_preferences WHERE prefKey = :key")
    suspend fun deletePreference(key: String)

    @Query("DELETE FROM bookmarks")
    suspend fun clearAllBookmarks()

    @Query("DELETE FROM user_notes")
    suspend fun clearAllNotes()
}
