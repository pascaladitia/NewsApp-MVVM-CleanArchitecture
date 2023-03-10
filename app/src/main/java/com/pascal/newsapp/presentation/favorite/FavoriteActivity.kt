package com.pascal.newsapp.presentation.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pascal.newsapp.R
import com.pascal.newsapp.databinding.ActivityFavoriteBinding
import com.pascal.newsapp.domain.model.Article
import com.pascal.newsapp.presentation.detail.DetailArticleActivity
import com.pascal.newsapp.utils.Constant
import com.pascal.newsapp.utils.hide
import com.pascal.newsapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity(), View.OnClickListener, FavoriteAdapter.Listener {

    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private val favoriteAdapter by lazy { FavoriteAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvName.text = getString(R.string.title_favorite)
        binding.ivBack.setOnClickListener(this)
        getFavoriteArticles()
    }

    override fun onClick(v: View?) {
        onBackPressedDispatcher.onBackPressed()
    }

    override fun listener(article: Article) {
        startActivity(
            Intent(this, DetailArticleActivity::class.java)
                .putExtra(Constant.EXTRA_DATA, article)
        )
    }

    private fun getFavoriteArticles() {
        favoriteViewModel.getFavoriteArticles.observe(this) {
            if (it.isNullOrEmpty()) {
                binding.layoutEmpty.linearEmpty.show()
            } else {
                favoriteAdapter.differ.submitList(it)
                binding.rvFavorite.apply {
                    layoutManager = LinearLayoutManager(this@FavoriteActivity)
                    adapter = favoriteAdapter
                }
                binding.layoutEmpty.linearEmpty.hide()
            }
        }
    }
}