package com.example.recipeapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import com.example.recipeapp.R
import com.example.recipeapp.ui.MainActivity
import com.example.recipeapp.ui.viewModel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_source.*

class SourceFragment : Fragment(R.layout.fragment_source) {

    private lateinit var homeviewModel: HomeFragmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeviewModel = (activity as MainActivity).homeFragmentViewModel

        homeviewModel.selectedRecipeLiveData.observe(viewLifecycleOwner, Observer {

            load_url(it.data?.meals?.get(0)?.strSource.toString())
        })

    }

    private fun load_url(url : String)
    {
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    }
}