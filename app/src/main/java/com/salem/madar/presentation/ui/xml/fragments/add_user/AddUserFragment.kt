package com.salem.madar.presentation.ui.xml.fragments.add_user

import android.content.Intent
import com.salem.madar.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.salem.madar.core.UiState
import com.salem.madar.databinding.FragmentAddUserBinding
import com.salem.madar.domain.models.User
import com.salem.madar.presentation.ui.compose.activities.ComposeActivity
import com.salem.madar.presentation.view_extensions.hide
import com.salem.madar.presentation.view_extensions.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class AddUserFragment : Fragment() {

    private var _binding: FragmentAddUserBinding? = null
    private val binding get() = _binding!!

    private val addUserMvvm: AddUserFragmentViewModel by viewModels()
    private val genderAdapter by lazy { GenderAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddUserBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        observeSaveUserState()

        // Reset Error
        resetErrorAfterTextChanged()


        // setup Gender Rec View
        setupGenderRecView()
        observeGenderState()

        // on Item Gender Selected Click
        onGenderSelected()

        observeSelectedGender()


        onNavigateToComposeExampleClick()



    }




    private fun setupListeners() {
        binding.save.setOnClickListener {
            val name = binding.nameEdit.text.toString().trim()
            val ageText = binding.ageEdit.text.toString().trim()
            val jobTitle = binding.jobTitleEdit.text.toString().trim()
            val gender = addUserMvvm.selectedGender.value?.genderType ?: ""

            when {
                name.isEmpty() -> {
                    binding.nameEdit.error = getString(R.string.name_required)
                    return@setOnClickListener
                }

                ageText.isEmpty() -> {
                    binding.ageEdit.error =getString(R.string.age_required)
                    return@setOnClickListener
                }

                jobTitle.isEmpty() -> {
                    binding.jobTitleEdit.error = getString(R.string.job_title_required)
                    return@setOnClickListener
                }

                gender.isEmpty() -> {
                    binding.tvGenderError.show()
                    return@setOnClickListener
                }
            }

            val age = ageText.toIntOrNull()
            if (age == null || age <= 0) {
                binding.ageEdit.error = getString(R.string.age_invalid)
                return@setOnClickListener
            }

            val user = User(
                name = name,
                age = age,
                jobTitle = jobTitle,
                gender = gender
            )

            addUserMvvm.upsertUser(user)
        }
    }


    private fun observeSaveUserState() {
        addUserMvvm.upsertUserResult.onEach { state ->

            binding.save.isEnabled = state !is UiState.Loading

            when (state) {
                is UiState.Loading -> {
                    binding.progressBar.show()
                }

                is UiState.Success -> {
                    binding.progressBar.hide()
                    Toast.makeText(requireContext(), "User saved", Toast.LENGTH_SHORT).show()
                    clearViews()
                    findNavController().navigate(R.id.showUsersFragment)
                }

                is UiState.Error -> {
                    binding.progressBar.hide()
                    Toast.makeText(
                        requireContext(),
                        "Error: ${state.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }


    private fun resetErrorAfterTextChanged() {
        binding.nameEdit.doAfterTextChanged { binding.nameEdit.error = null }
        binding.ageEdit.doAfterTextChanged { binding.ageEdit.error = null }
        binding.jobTitleEdit.doAfterTextChanged { binding.jobTitleEdit.error = null }

    }


    private fun setupGenderRecView() {
        binding.recViewGender.adapter = genderAdapter
    }

    private fun observeGenderState() {
        addUserMvvm.genders.onEach {
            genderAdapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }


    private fun onGenderSelected() {
        genderAdapter.onGenderSelected = {
            addUserMvvm.onSelectedGender(it)
        }
    }


    private fun observeSelectedGender() {
        addUserMvvm.selectedGender.onEach { gender ->
            if (gender != null) {  binding.tvGenderError.hide()  }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }



    private fun clearViews(){
        binding.nameEdit.text.clear()
        binding.ageEdit.text.clear()
        binding.jobTitleEdit.text.clear()
        addUserMvvm.clearSelectedGender()
        genderAdapter.clearSelection()

    }


    private fun onNavigateToComposeExampleClick() {
        binding.composeActivity.setOnClickListener {
            val intent = Intent(requireContext(), ComposeActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        genderAdapter.onGenderSelected = null
    }
}