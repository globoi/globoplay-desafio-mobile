package com.reisdeveloper.globoplay.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.reisdeveloper.globoplay.ui.components.ShimmerLoadingView

abstract class BaseFragment<T : ViewBinding, VM : BaseViewModel>(
    private val inflateMethod: (LayoutInflater, ViewGroup?, Boolean) -> T
) : Fragment() {

    private var _binding: T? = null
    val binding: T get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflateMethod.invoke(inflater, container, false)
        return binding.root
    }

    abstract val viewModel: VM

    open fun onBackPressed() {
        findNavController().popBackStack()
    }

    fun showError(error: String) {
        view?.let {
            Snackbar.make(it, error, Snackbar.LENGTH_LONG).show()
        }
    }

    fun shimmerLoading(
        viewGroup: ViewGroup,
        loadLayout: Int,
        loading: Boolean
    ) {
        if (loading) {
            ShimmerLoadingView.show(viewGroup, loadLayout)
        } else {
            ShimmerLoadingView.hide(viewGroup)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}