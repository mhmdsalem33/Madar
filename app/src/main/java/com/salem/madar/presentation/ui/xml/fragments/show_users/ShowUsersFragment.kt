package com.salem.madar.presentation.ui.xml.fragments.show_users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.salem.madar.databinding.FragmentShowUsersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ShowUsersFragment : Fragment() {


    private var _binding: FragmentShowUsersBinding? = null
    private val binding get() = _binding!!

    private val showUsersMvvm: ShowUsersFragmentViewModel by viewModels()

    private val showUsersAdapter by lazy { ShowUsersAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowUsersBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupUsersRecView()
        observeUsers()


    }

    private fun setupUsersRecView() {
        binding.recViewUsers.adapter = showUsersAdapter
    }

    private fun observeUsers() {
        showUsersMvvm.usersState.onEach { users ->
            if (users.isNotEmpty()) {
                showUsersAdapter.submitList(users)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}