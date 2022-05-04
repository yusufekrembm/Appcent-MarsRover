package com.yusufekrem.nasaproject.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yusufekrem.nasaproject.adapter.MarsAdapter
import com.yusufekrem.nasaproject.R
import com.yusufekrem.nasaproject.databinding.ActivityMainBinding
import com.yusufekrem.nasaproject.model.Data
import com.yusufekrem.nasaproject.viewmodel.MarsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    // view binding for the activity
    lateinit var binding : ActivityMainBinding

    private lateinit var marsViewModel : MarsViewModel
    private lateinit var marsAdapter : MarsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        marsViewModel = ViewModelProviders.of(this).get(MarsViewModel::class.java)
        setUI()
    }


    private fun setUI() {
        setActionBar()
        getDataFromAPI()
    }

    private fun setActionBar(){
        val actionbar: ActionBar? = supportActionBar
        actionbar?.title = "Mars Photos"
        actionbar?.elevation = 4.toFloat()
        actionbar?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(this,
                    R.color.black
                ))
        )
    }

    private fun getDataFromAPI() {
        marsViewModel.data.observe(this, Observer { data ->
            marsAdapter = MarsAdapter(photos = data.photos){

                // Pop Up Intent :(
                val intent = Intent(this@MainActivity, PopUpActivity::class.java)
                intent.putExtra("id",it)
                startActivity(intent)

            }
            recyclerView.layoutManager = GridLayoutManager(this@MainActivity,3)
            recyclerView.adapter = marsAdapter
        })
    }
    // search menu inflater
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        menu.findItem(R.id.menu_search)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.menu_search)
        var searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("",false)
                searchItem.collapseActionView()
                Toast.makeText(this@MainActivity,"Looking for query  ",Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }
}