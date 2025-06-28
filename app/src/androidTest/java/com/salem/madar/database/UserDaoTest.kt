package com.salem.madar.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.salem.madar.data.data_source.local.roomDB.dao.UserDao
import com.salem.madar.data.data_source.local.roomDB.database.AppDatabase
import com.salem.madar.domain.models.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat

@RunWith(AndroidJUnit4::class)
class UserDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var userDao: UserDao


    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        userDao = database.userDao()
    }

    @After
    fun clearDatabase() {
        database.close()
    }


    @Test
    fun upsertUser_shouldInsertUserCorrectly() = runTest {
        val user = User(
            id = 1,
            name = "Salem",
            age = 25,
            jobTitle = "android developer",
            gender = "Male"
        )
        userDao.upsertUser(user)


        val result = userDao.getAllUsers().first()
        assertThat(result).contains(user)
    }

    @Test
    fun upsertUser_shouldReplaceIfConflict() = runTest {
        val user1 = User(
            id = 1,
            name = "Old",
            age = 20,
            jobTitle = "IOS developer",
            gender = "Male"
        )

        val user2 = User(
            id = 1,
            name = "Updated",
            age = 25,
            jobTitle = "Flutter developer",
            gender = "Male"
        )

        userDao.upsertUser(user1)
        userDao.upsertUser(user2)

        val firstUser = userDao.getAllUsers().first()

        assertThat(firstUser.size).isEqualTo(1)
        assertThat(firstUser[0].name).isEqualTo("Updated") // user information should updated success if every thing is ok
    }


    @Test
    fun insertMultipleUsers_shouldInsertAll() = runTest {
        val users = listOf(
            User(1, "Salem", 25, "Android", "Male"),
            User(2, "Fatma", 23, "Backend", "Female"),
            User(3, "Youssef", 30, "Tester", "Male")
        )

        users.forEach { userDao.upsertUser(it) }

        val result = userDao.getAllUsers().first()
        assertThat(result).hasSize(3)
        assertThat(result).containsExactlyElementsIn(users)
    }


    @Test
    fun getUsers_shouldReturnEmptyList_whenNoUserExists() = runTest {
        val users = userDao.getAllUsers()
        assertThat(users)
    }


}