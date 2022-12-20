package com.example.testnewsaplication.screns.webview_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.testnewsaplication.util.MAIN
import com.example.testnewsaplication.R
import com.example.testnewsaplication.databinding.FragmentViewWebBinding

class ViewWebFragment : Fragment() {
    lateinit var binding: FragmentViewWebBinding
    private lateinit var newsUrl: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentViewWebBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsUrl= arguments?.getSerializable("newsUrl") as String
        webViewNews()

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewNews(){
        binding.webView.webViewClient= WebViewClient()
        binding.webView.apply {
            loadUrl(newsUrl)
            settings.javaScriptEnabled=true
        }
        binding.floatingActionButton.setOnClickListener{
            MAIN.navController.navigate(R.id.action_viewWebFragment_to_mainFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MayLog","onDestroy")
    }

}