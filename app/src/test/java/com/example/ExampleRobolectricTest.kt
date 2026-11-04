package com.example

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.AnesthesiaDao
import com.example.data.local.AnesthesiaDatabase
import com.example.data.local.BookmarkEntity
import com.example.data.local.UserNoteEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    private lateinit var database: AnesthesiaDatabase
    private lateinit var dao: AnesthesiaDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AnesthesiaDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.anesthesiaDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testReadAppNameString() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Anesthesia Pro", appName)
    }

    @Test
    fun testRoomDatabaseBookmarks() = runBlocking {
        val bookmark = BookmarkEntity(
            id = "lesson_airway_assessment",
            type = "lesson",
            title = "Comprehensive Airway Assessment",
            subtitle = "Anesthesia Foundations"
        )
        dao.insertBookmark(bookmark)

        val bookmarks = dao.getAllBookmarks().first()
        assertEquals(1, bookmarks.size)
        assertEquals("lesson_airway_assessment", bookmarks[0].id)
        assertTrue(dao.isBookmarked("lesson_airway_assessment").first())

        dao.deleteBookmark("lesson_airway_assessment")
        val emptyBookmarks = dao.getAllBookmarks().first()
        assertTrue(emptyBookmarks.isEmpty())
    }

    @Test
    fun testRoomDatabaseUserNotes() = runBlocking {
        val note = UserNoteEntity(
            targetId = "drug_propofol",
            targetType = "drug",
            targetTitle = "Propofol",
            content = "Clinical Pearl: Reduce induction dose to 1.0 mg/kg in shock or elderly"
        )
        dao.insertNote(note)

        val notes = dao.getNotesForTarget("drug_propofol").first()
        assertEquals(1, notes.size)
        assertEquals("Propofol", notes[0].targetTitle)
        assertTrue(notes[0].content.contains("Clinical Pearl"))
    }
}
