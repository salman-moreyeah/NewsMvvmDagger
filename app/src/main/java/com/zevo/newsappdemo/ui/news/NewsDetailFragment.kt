package com.zevo.newsappdemo.ui.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.zevo.newsappdemo.databinding.FragmentNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        catchIntent()
        return binding.root
    }

    private fun catchIntent() {
        val url = arguments?.getString("URL")
        if (url != null) {
            binding.webView.loadUrl(url)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}