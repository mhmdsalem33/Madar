package com.salem.madar.repo

import com.google.common.truth.Truth.assertThat
import com.salem.madar.data.data_source.local.roomDB.dao.UserDao
import com.salem.madar.data.repo.GetUsersRepositoryImpl
import com.salem.madar.domain.models.User
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetUsersRepositoryImplTest {
    @Test
    fun `get all saved users returns correct data`() = runTest {

        val expectedUsers = listOf(
            User(1, "Salem", 25, "Android", "Male"),
            User(2, "Fatma", 23, "Backend", "Female")
        )
        val fakeUserDao = mockk<UserDao>()

        coEvery { fakeUserDao.getAllUsers() } returns flowOf(expectedUsers)

        val repository = GetUsersRepositoryImpl(fakeUserDao)

        val users = repository.getUsers().first()

        assertThat(users).hasSize(2)
        assertThat(users).containsExactlyElementsIn(expectedUsers)
    }
}