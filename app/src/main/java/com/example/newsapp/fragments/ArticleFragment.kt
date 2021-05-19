package com.example.newsapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newsapp.MainActivity
import com.example.newsapp.R
import com.example.newsapp.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel
    val articleFragmentArgs: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).vm
        val article = articleFragmentArgs.article
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

        fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Saved article", Snackbar.LENGTH_SHORT).show()
        }

        share.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            val body = "Your body here"
            val sub = "Your Subject"
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Sharing news from Smart News App")
            sendIntent.putExtra(Intent.EXTRA_TEXT, article.title + "\n\n" + article.description + "\n\n" + article.url)
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
    }

}