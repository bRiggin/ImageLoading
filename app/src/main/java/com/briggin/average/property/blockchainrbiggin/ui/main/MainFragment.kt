package com.briggin.average.property.blockchainrbiggin.ui.main

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.briggin.average.property.blockchainrbiggin.R
import com.briggin.average.property.blockchainrbiggin.presentation.MainViewModel
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class MainFragment : Fragment(R.layout.main_fragment), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext get() = Dispatchers.Default + job

    private val viewModel: MainViewModel by viewModel()

    private var progressBar: ProgressBar? = null
    private var errorMessage: TextView? = null
    private var dogImage: ImageView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progressBar)
        errorMessage = view.findViewById(R.id.errorTextView)
        dogImage = view.findViewById(R.id.dogImageView)

        observeViewModel()
        launch { viewModel.viewLoaded() }
    }

    private fun observeViewModel() {
        viewModel.progressBar.observe(viewLifecycleOwner, { progressBar?.visibility = it })
        viewModel.errorMessage.observe(viewLifecycleOwner, {
            errorMessage?.visibility = it.visibility
            errorMessage?.text = it.payload
        })
        viewModel.dogImage.observe(viewLifecycleOwner, {
            dogImage?.visibility = it.visibility
            dogImage?.setImageResource(R.drawable.ic_launcher_background)
        })
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}