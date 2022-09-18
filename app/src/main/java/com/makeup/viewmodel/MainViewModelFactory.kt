package com.makeup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.makeup.model.repository.Repository


/* This is the Factory for the MainViewModel, it is responsible for instantiating
the Viewmodel
 */
class MainViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}