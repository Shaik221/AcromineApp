package com.example.acromineapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.example.acromineapp.adapter.CustomAdapter
import com.example.acromineapp.databinding.ActivityMainBinding
import com.example.acromineapp.network.AcronymApi
import com.example.acromineapp.vm.MainActivityViewModel
import com.example.acromineapp.vm.MainActivityViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var viewModelFactory: MainActivityViewModelFactory

    private val viewModel: MainActivityViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var acronymApi: AcronymApi

    private lateinit var binding:ActivityMainBinding

    private lateinit var progressBar: ProgressBar
    private lateinit var emptyText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        (application as BaseApplication).getAppComponent().inject(this)

        progressBar = binding.progressBar
        emptyText = binding.emptyText

        viewModelFactory = MainActivityViewModelFactory(acronymApi)
        binding.vm = viewModel

        observeData()
    }

    private fun observeData() {
        val adapter = CustomAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.viewItems.observe(this) { items ->
            if (items.isEmpty()) {
                adapter.clearList()
                emptyText.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            } else {
                adapter.submitList(items[0].sf, items[0].lfs)
                emptyText.visibility = View.GONE
                progressBar.visibility = View.GONE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)

        // below line is to get our menu item.
        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)

        // getting search view of our item.
        val searchView: SearchView = searchItem.actionView as SearchView

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    progressBar.visibility = View.VISIBLE

                    if (containsWhitespace(it)) {
                        viewModel.getAcronymDetails("lf", it)
                    } else {
                        viewModel.getAcronymDetails("sf", it)
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                progressBar.visibility = View.GONE
                return false
            }
        })
        return true
    }

    fun containsWhitespace(str: String): Boolean {
        val regexPattern = """.*\s.*"""
        return str.matches(regexPattern.toRegex())
    }

}