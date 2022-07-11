package com.nroncari.movieplay.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StateAppComponentsViewModel : ViewModel() {

    val components: LiveData<VisualComponents> get() = _components

    private var _components: MutableLiveData<VisualComponents> =
        MutableLiveData<VisualComponents>().also {
            it.value = havComponent
        }

    var havComponent: VisualComponents = VisualComponents(false)
        set(value) {
            field = value
            _components.value = value
        }
}

class VisualComponents(
    val bottomNavigation: Boolean = false
)