package com.example.androidherexamen

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.androidherexamen.database.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class DatabaseTests {

    private lateinit var postDatabaseDAO: PostDatabaseDAO
    private lateinit var commentDatabaseDAO: CommentDatabaseDAO
    private lateinit var userDatabaseDAO: UserDatabaseDAO

    private lateinit var db: Database

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, Database::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        postDatabaseDAO = db.postDatabaseDAO
        commentDatabaseDAO = db.commentDatabaseDAO
        userDatabaseDAO = db.userDatabaseDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
 /*
    @Test
    @Throws(Exception::class)
    fun insertAndGetPost() {
        val post = Post()
        postDatabaseDAO.insert(post)
        val newPost = postDatabaseDAO.getAllByUserId()
        assertEquals(tonight?.sleepQuality, -1)
    }
  */
}