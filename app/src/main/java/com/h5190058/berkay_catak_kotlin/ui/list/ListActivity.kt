package com.h5190058.berkay_catak_kotlin.ui.list

import AdsModelItem
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.h5190058.berkay_catak_kotlin.R
import com.h5190058.berkay_catak_kotlin.ui.detail.DetailActivity
import com.h5190058.berkay_catak_kotlin.util.Constants
import com.h5190058.berkay_catak_kotlin.util.ObjectUtil
import com.h5190058.berkay_catak_kotlin.util.ProgressUtil


class ListActivity : AppCompatActivity() {
    var listViewModel: ListViewModel?= null
    var adsList = ArrayList<AdsModelItem>()
    private lateinit var listAdapter: ListAdapter
    var listContext = this
    private var progressDialog: ProgressDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {

        val categoryId: String ?= intent.getStringExtra(Constants.KATEGORI_TASI)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        getAds(categoryId.toString())
        changeGridView()
        initSpinner(categoryId.toString())
    }

    private fun initSpinner(categoryId: String) {
        val spinner = findViewById(R.id.listSpinner) as Spinner
        //var artan = R.string.spinner_artan as String
        //var azalan = R.string.spinner_azalan as String
        val lessonsList: MutableList<String> = mutableListOf("Artan (Z-A)", "Azalan (A-Z)")
        val adapter = ArrayAdapter(this@ListActivity, android.R.layout.simple_spinner_item, lessonsList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position == 0){
                    var filteredAdList = adsList.sortedBy {
                        it.baslik
                    }.filter { it.kategoriid == categoryId }
                    initRecyclerView(filteredAdList)
                }else{
                    var filteredAdList = adsList.sortedByDescending {
                        it.baslik
                    }.filter { it.kategoriid == categoryId }
                    initRecyclerView(filteredAdList)
                }
            }
        }
    }

    private fun changeGridView() {
        val switch = findViewById(R.id.switch1) as Switch
        switch.setOnCheckedChangeListener { button, isChecked ->
            val recyclerIlanlar = findViewById(R.id.RecyclerViewIlanlar) as RecyclerView
            if (switch.isChecked) {
                recyclerIlanlar.apply { layoutManager = GridLayoutManager(this@ListActivity, 2) }
            } else {
                recyclerIlanlar.apply { layoutManager = GridLayoutManager(this@ListActivity, 1) }
            }
        }
    }

    fun getAds(categoryId: String){
        progressDialog = ProgressUtil.progressOlustur(this@ListActivity, resources.getString(R.string.progressMessage))

        listViewModel = ListViewModel();

        listViewModel?.apply {

            listLiveData?.observe(this@ListActivity, Observer {
                it.run {
                    ProgressUtil.progressKapat(progressDialog)
                    adsList = it as ArrayList<AdsModelItem>
                    var filteredAdList = adsList.sortedBy {
                        it.baslik
                    }.filter { it.kategoriid == categoryId }
                    initRecyclerView(filteredAdList)
                }
            })

            error?.observe(this@ListActivity, Observer {
                it.run {
                    Toast.makeText(applicationContext,this.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })

            loading?.observe(this@ListActivity, Observer {
                //Handle loading
            })
        }

    }


    @SuppressLint("WrongConstant")
    private fun initRecyclerView(_filteredAdList: List<AdsModelItem>?) {
        Log.d("son", _filteredAdList?.size.toString())

        val recyclerIlanlar = findViewById(R.id.RecyclerViewIlanlar) as RecyclerView
        recyclerIlanlar.layoutManager = LinearLayoutManager(this@ListActivity, LinearLayout.VERTICAL, false)
        recyclerIlanlar.apply { layoutManager = GridLayoutManager(this@ListActivity, 1) }

        listAdapter = ListAdapter(_filteredAdList as ArrayList<AdsModelItem>, object : ListAdapter.ItemClickListener {
            override fun onIemClick(position: Int) {
                val ilan: AdsModelItem = _filteredAdList.get(position)
                openActivityDetail(position, ilan, listContext)
            }
        }
        )
        recyclerIlanlar.adapter = listAdapter
    }

    fun openActivityDetail(position : Int, list : AdsModelItem, listContext : Context)
    {
        val intent = Intent(listContext, DetailActivity::class.java)
        val ilan : String = ObjectUtil.objectToString(list)
        intent.putExtra(Constants.ILAN_TASI, ilan)
        listContext.startActivity(intent)
    }


}