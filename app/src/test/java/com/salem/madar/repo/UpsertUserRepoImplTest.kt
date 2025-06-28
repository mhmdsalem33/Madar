package com.salem.madar.repo

import com.google.common.truth.Truth.assertThat
import com.salem.madar.data.data_source.local.roomDB.dao.UserDao
import com.salem.madar.data.repo.UpsertUserRepoImpl
import com.salem.madar.domain.models.User
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UpsertUserRepoImplTest {

    @Test
    fun `upsertUser returns true when DAO returns valid ID`() = runTest {
        val fakeUserDao = mockk<UserDao>()
        val repository = UpsertUserRepoImpl(fakeUserDao)

        val user = User(1, "Salem", 25, "Android", "Male")
        coEvery { fakeUserDao.upsertUser(user) } returns 1L

        val result = repository.upsertUser(user)

        assertThat(result).isTrue()
    }
}