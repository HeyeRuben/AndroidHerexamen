package com.example.androidherexamen

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.androidherexamen.database.*
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MyDatabaseTests {

    private lateinit var postDatabaseDAO: PostDatabaseDAO
    private lateinit var commentDatabaseDAO: CommentDatabaseDAO
    private lateinit var userDatabaseDAO: UserDatabaseDAO

    private lateinit var db: MyDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, MyDatabase::class.java)
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

    @Test
    @Throws(Exception::class)
    suspend fun insertAndCountShouldBePlusOne() {
        val currentAmount = postDatabaseDAO.getAllNieuwePosts().value?.size
        val post = Post()
        postDatabaseDAO.insert(post)
        val newPosts = postDatabaseDAO.getAllNieuwePosts().value?.size
        if (currentAmount != null) {
            assertEquals(currentAmount + 1, newPosts)
        }
    }
}