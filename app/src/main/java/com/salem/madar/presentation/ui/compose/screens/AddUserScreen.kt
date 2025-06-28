package com.salem.madar.presentation.ui.compose.screens


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.salem.foodapp.presentation.widgets.spaces.SpaceHeight10
import com.salem.foodapp.presentation.widgets.spaces.SpaceHeight20
import com.salem.madar.R
import com.salem.madar.core.UiState
import com.salem.madar.domain.models.GenderModel
import com.salem.madar.domain.models.User
import com.salem.madar.presentation.custom.widget.CustomOutlinedTextField
import com.salem.madar.presentation.custom.widget.CusttomButton
import com.salem.madar.presentation.navigation.ShowUsersScreen
import com.salem.madar.presentation.ui.compose.theme.MadarTheme
import com.salem.madar.presentation.ui.xml.fragments.add_user.AddUserFragmentViewModel


@Composable
fun AddUserScreen(
    modifier: Modifier = Modifier,
    viewModel: AddUserFragmentViewModel = hiltViewModel(),
    navController: NavHostController? = null,

    ) {

    val localFocusManager = LocalFocusManager.current


    Column(
        modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 50.dp
            )
    ) {


        var nameError by remember { mutableStateOf<String?>(null) }
        var ageError by remember { mutableStateOf<String?>(null) }
        var jobTitleError by remember { mutableStateOf<String?>(null) }
        var genderError by remember { mutableStateOf<String?>(null) }
//

        // Start Enter Name  Outline text field
        var name by remember { mutableStateOf("") }

        CustomOutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = null
            },
            placeholderText = stringResource(id = R.string.enter_your_name),
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            trailingIcon = {
                if (name.isNotEmpty()) {
                    Image(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "clear icon",
                        modifier = Modifier.clickable { name = "" }
                    )
                }
            },
            onImeAction = {
                localFocusManager.moveFocus(FocusDirection.Down)
            }
        )


        if (nameError != null) {
            SpaceHeight10()
            Text(
                text = nameError!!,
                color = Color.Red,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 4.dp, top = 2.dp)
            )
        }

        // End Enter Name  Outline text field

        SpaceHeight20()

        //  Start Enter age  Outline text field
        var age by remember { mutableStateOf("") }

        CustomOutlinedTextField(
            value = age,
            onValueChange = {
                age = it
                ageError = null
            },
            placeholderText = stringResource(id = R.string.enter_your_age),
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next,
            trailingIcon = {
                if (age.isNotEmpty()) {
                    Image(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "clear icon",
                        modifier = Modifier.clickable { age = "" }
                    )
                }
            },
            onImeAction = {
                localFocusManager.moveFocus(FocusDirection.Down)
            }
        )


        if (ageError != null) {
            SpaceHeight10()
            Text(
                text = ageError!!,
                color = Color.Red,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 4.dp, top = 2.dp)
            )
        }

        //  End Enter age  Outline text field


        SpaceHeight20()

        // Start Enter Job Title  Outline text field
        var jobTitle by remember { mutableStateOf("") }

        CustomOutlinedTextField(
            value = jobTitle,
            onValueChange = {
                jobTitle = it
                jobTitleError = null
            },
            placeholderText = stringResource(id = R.string.job_title),
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done,
            trailingIcon = {
                if (jobTitle.isNotEmpty()) {
                    Image(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "clear icon",
                        modifier = Modifier.clickable { jobTitle = "" }
                    )
                }
            },
            onImeAction = {
                localFocusManager.moveFocus(FocusDirection.Down)
            }
        )


        if (jobTitleError != null) {
            SpaceHeight10()
            Text(
                text = jobTitleError!!,
                color = Color.Red,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 4.dp, top = 2.dp)
            )
        }

        // End Enter Job Title  Outline text field

        SpaceHeight20()

        // Start  Gender
        val genderList by viewModel.genders.collectAsState()

        GenderList(genders = genderList) { gender ->
            viewModel.onSelectedGender(gender)
        }

        if (genderError != null) {

            SpaceHeight10()
            Text(
                text = genderError!!,
                color = Color.Red,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 4.dp, top = 2.dp)
            )
        }

        // End  Gender

        SpaceHeight20()


        // Save Button

        CusttomButton(onClick = {
            var isValid = true

            if (name.isBlank()) {
                nameError = "Please enter your name"
                isValid = false
            } else {
                nameError = null
            }

            if (age.isBlank() || age.toIntOrNull() == null || age.toInt() !in 1..120) {
                ageError = "Please enter a valid age"
                isValid = false
            } else {
                ageError = null
            }

            if (jobTitle.isBlank()) {
                jobTitleError = "Please enter your job title"
                isValid = false
            } else {
                jobTitleError = null
            }

            val gender = viewModel.selectedGender.value?.genderType ?: ""

            if (gender.isEmpty()) {
                genderError = "Please select a gender"
                isValid = false
            } else {
                genderError = null
            }

            if (isValid) {

                val user = User(
                    name = name,
                    age = age.toInt(),
                    jobTitle = jobTitle,
                    gender = gender
                )

                viewModel.upsertUser(user)
            }
        })
        SpaceHeight20()

        CusttomButton(
            onClick = {
                navController?.navigate(ShowUsersScreen)
            },
            textResId = R.string.show_saved_users
        )



        ObserveSaveUserState(viewModel, navController)

    }
}

@Composable
private fun ObserveSaveUserState(
    viewModel: AddUserFragmentViewModel,
    navController: NavHostController?
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.upsertUserResult.collect { state ->
            when (state) {

                is UiState.Success -> {
                    Toast.makeText(context, "User saved successfully!", Toast.LENGTH_SHORT).show()
                    navController?.navigate(ShowUsersScreen)
                }

                is UiState.Error -> {
                    Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
                }

                else -> Unit
            }
        }
    }

}


@Composable
fun GenderItem(
    gender: GenderModel,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor =
        if (isSelected) colorResource(id = R.color.blue_transparent) else colorResource(id = R.color.gray_1)

    val textColor = if (isSelected) Color.White else Color.Gray

    Text(
        text = "${gender.genderType} ${gender.emoji}",
        modifier = Modifier
            .padding(end = 5.dp)
            .background(backgroundColor, shape = RoundedCornerShape(14.dp))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            }
            .padding(horizontal = 12.dp, vertical = 8.dp),
        color = textColor,
        style = MaterialTheme.typography.body1
    )
}


@Composable
fun GenderList(
    genders: List<GenderModel>,
    modifier: Modifier = Modifier,
    onGenderSelected: (GenderModel) -> Unit
) {

    var selectedIndex by remember { mutableIntStateOf(-1) }

    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(end = 5.dp)
    ) {
        itemsIndexed(genders, key = { _, item -> item.id }) { index, gender ->
            GenderItem(
                gender = gender,
                isSelected = index == selectedIndex,
                onClick = {
                    selectedIndex = index
                    onGenderSelected(gender)
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddScreenPreview() {
    MadarTheme {
        AddUserScreen()
    }
}