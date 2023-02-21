package com.pascal.newsapp.presentation.news

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pascal.newsapp.R
import com.pascal.newsapp.data.source.Resource
import com.pascal.newsapp.databinding.ActivityMainBinding
import com.pascal.newsapp.domain.model.News
import com.pascal.newsapp.presentation.article.ArticleActivity
import com.pascal.newsapp.presentation.favorite.FavoriteActivity
import com.pascal.newsapp.utils.Constant
import com.pascal.newsapp.utils.hide
import com.pascal.newsapp.utils.show
import com.pascal.newsapp.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), TextWatcher, MainAdapter.OnItemClickCallBack,
    View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
        binding.etSearch.addTextChangedListener(this)
        binding.ivCancel.setOnClickListener(this)
        binding.ivBookmark.setOnClickListener(this)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//        TODO("Not yet implemented")
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.isEmpty()) {
            binding.ivCancel.visibility = View.GONE
        } else {
            binding.ivCancel.visibility = View.VISIBLE
            try {
                mainAdapter.filter.filter(s)
            } catch (e: Exception) {
                e.message
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {
//        TODO("Not yet implemented")
    }

    override fun onItemClicked(data: News) {
        startActivity(
            Intent(this, ArticleActivity::class.java)
                .putExtra(Constant.EXTRA_DATA, data)
        )
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_cancel -> {
                binding.etSearch.setText("")
                try {
                    val searchTxt = binding.etSearch.text.toString()
                    mainAdapter.filter.filter(searchTxt)
                } catch (e: Exception) {
                    e.localizedMessage
                }
            }
            R.id.iv_bookmark -> {
                startActivity(Intent(this, FavoriteActivity::class.java))
            }
        }
    }

    private fun getData() {
        mainViewModel.news.observe(this) { response ->
            if (response != null) {
                when (response) {
                    is Resource.Loading -> {
                        binding.progressCircular.show()
                    }
                    is Resource.Success -> {
                        val data = response.data
                        if (data.isNullOrEmpty()) {
                            binding.layoutEmpty.linearEmpty.show()
                        } else {
                            mainAdapter = MainAdapter(data as MutableList<News>, this)
                            binding.rvNews.apply {
                                layoutManager = LinearLayoutManager(this@MainActivity)
                                adapter = mainAdapter
                            }
                        }
                        binding.progressCircular.hide()
                    }
                    is Resource.Error -> {
                        toast(response.message.toString())
                        binding.progressCircular.hide()
                    }
                }
            }
        }
    }
}