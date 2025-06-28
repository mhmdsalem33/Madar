package com.salem.madar.presentation.ui.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.salem.madar.R
import com.salem.madar.domain.models.User
import com.salem.madar.presentation.custom.widget.LabelWithValue
import com.salem.madar.presentation.ui.xml.fragments.show_users.ShowUsersFragmentViewModel

@Composable
fun ShowUsersScreen(
    showUsersMvvm  : ShowUsersFragmentViewModel = hiltViewModel()
){
    Box (modifier = Modifier.fillMaxSize().background(Color.White)){
        val users by showUsersMvvm.usersState.collectAsState()
        UserList(users = users)
    }
}


@Composable
fun UserList(users: List<User>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier =
            modifier.padding(
            start = 16.dp,
            end = 16.dp,
            top = 30.dp
        ),

    ) {
        itemsIndexed(users, key = { _, user -> user.id }) { index, user ->
            UserItem(
                user = user,
                showDivider = index < users.size - 1
            )
        }
    }
}

@Composable
fun UserItem(
    user: User,
    showDivider: Boolean = true,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(vertical = 8.dp)) {

        // Name
        LabelWithValue(
            label = stringResource(id = R.string.name),
            value = user.name
        )

        // Age
        LabelWithValue(
            label = stringResource(id = R.string.age),
            value = "${user.age} ${stringResource(id = R.string.user_gender_age)}"
        )

        // Job Title
        LabelWithValue(
            label = stringResource(id = R.string.job_title_),
            value = user.jobTitle
        )

        // Gender
        LabelWithValue(
            label = stringResource(id = R.string.gender),
            value = user.gender
        )

        // Divider
        if (showDivider) {
            Divider(
                color = Color.Black,
                thickness = 1.dp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

