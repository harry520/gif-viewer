package com.example.gifviewer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.gifviewer.R
import com.tenor.android.core.model.impl.Result
import com.tenor.android.core.response.impl.GifsResponse
import com.tenor.android.core.response.impl.TrendingGifResponse

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var gifsRecyclerView: RecyclerView
    private lateinit var searchInput: EditText
    private lateinit var clear: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gifsRecyclerView = findViewById(R.id.gifs_rv)
        searchInput = findViewById(R.id.ETSearch)
        clear = findViewById(R.id.IVClear)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getTrendingGIFs(50, "")
        mainViewModel.trendingGifsResponse.observe(this, Observer {
            displayTrendingGifs(it)
        })
        mainViewModel.searchResultsResponse.observe(this, Observer {
            displaySearchResults(it)
        })
        clear.setOnClickListener {
            searchInput.text.clear()
        }
        searchInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (searchInput.text != null && searchInput.text.toString().isNotEmpty())
                    mainViewModel.getSearchResults(searchInput.text.toString(), 50, "")
                else
                    Toast.makeText(this, "Enter something to search for.", Toast.LENGTH_SHORT)
                        .show()
                true
            } else
                false
        }
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty() && s.toString().isNotEmpty()) {
                    if (s.toString().isNotEmpty()) {
                        mainViewModel.getSearchResults(s.toString().trim(), 50, "")
                    }
                } else
                    mainViewModel.getTrendingGIFs(50, "")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty() || s == "") {
                    mainViewModel.getTrendingGIFs(50, "")
                } else
                    mainViewModel.getSearchResults(s.toString().trim(), 50, "")
            }

        })
    }

    private fun displayTrendingGifs(it: TrendingGifResponse?) {
        if (it != null) {
            gifsRecyclerView.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            gifsRecyclerView.adapter =
                GifsAdapter(it.results as ArrayList<Result>)
        }
    }

    private fun displaySearchResults(it: GifsResponse?) {
        if (it != null) {
            gifsRecyclerView.layoutManager =
                StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            gifsRecyclerView.adapter =
                GifsAdapter(it.results as ArrayList<Result>)
        }
    }

}
