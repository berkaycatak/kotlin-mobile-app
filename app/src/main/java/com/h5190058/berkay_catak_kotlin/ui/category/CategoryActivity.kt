package com.h5190058.berkay_catak_kotlin.ui.category

import AdsModelItem
import CategoriesModelItem
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.h5190058.berkay_catak_kotlin.R
import com.h5190058.berkay_catak_kotlin.ui.list.ListActivity
import com.h5190058.berkay_catak_kotlin.util.Constants
import com.h5190058.berkay_catak_kotlin.util.ProgressUtil
import com.h5190058.berkay_catak_kotlin.util.getImage

class CategoryActivity : AppCompatActivity() {
    var categoryViewModel: CategoryViewModel?=null
    var categoryList = ArrayList<CategoriesModelItem>()
    private var progressDialog: ProgressDialog?=null

    private lateinit var categoryAdapter: CategoryAdapter
    val categoryContext = this

    override fun onCreate(savedInstanceState: Bundle?) {
        val userName: String ?= intent.getStringExtra(Constants.USER_NAME)
        val userPp : String  ?= intent.getStringExtra(Constants.USER_PHOTO)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        init(userName , userPp)
    }

    private fun init(userName: String?, userPp: String?) {
        progressDialog = ProgressUtil.progressOlustur(this@CategoryActivity, resources.getString(R.string.progressMessage))
        getCategories()
        initUserValues(userName, userPp)
        initSearch()
    }

    private fun initUserValues(userName: String?, userPp: String?) {
        val txtViewUserName = findViewById(R.id.txtViewUserNameCategory) as TextView
        val imageViewProfilePhoto = findViewById(R.id.ImageViewUserPpCategory) as ImageView

        txtViewUserName.text = userName
        if (userPp != null) {
            imageViewProfilePhoto.getImage(userPp)
        }
    }

    private fun initSearch() {
        val searchView = findViewById(R.id.searchViewCategories) as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                categoryFilter(query.toString())
                return true;
            }
        })
    }


    override fun onBackPressed() {
        // AlertDialog nesnemizi üretiyoruz
        val alert = AlertDialog.Builder(this)

        alert.setTitle(resources.getString(R.string.eminMisiniz))
        alert.setMessage(resources.getString(R.string.eminMisinizMesaj))
        alert.setCancelable(false);
        alert.setPositiveButton(resources.getString(R.string.evet)) { dialogInterface: DialogInterface, i: Int ->
            finish()
        }

        alert.setNegativeButton(resources.getString(R.string.hayır)) { dialogInterface: DialogInterface, i: Int ->
        }
        alert.show()
    }

    fun categoryFilter(text : String){
        var filteredCategoryList = categoryList.filter { it.name.contains(text) }
        initRecyclerView(filteredCategoryList)
    }

    fun getCategories(){
        categoryViewModel =
            CategoryViewModel();

        categoryViewModel?.apply {

            categoriesLiveData?.observe(this@CategoryActivity, Observer {

                it.run {
                    ProgressUtil.progressKapat(progressDialog)
                    categoryList = it as ArrayList<CategoriesModelItem>
                    initRecyclerView(categoryList)
                }
            })

            error?.observe(this@CategoryActivity, Observer {

                it.run {
                    Toast.makeText(applicationContext,this.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })

            loading?.observe(this@CategoryActivity, Observer {

                //Handle loading
            })
        }

    }

    @SuppressLint("WrongConstant")
    private fun initRecyclerView(it: List<CategoriesModelItem>?) {
        val recyclerCategories = findViewById(R.id.RecyclerCategories) as RecyclerView
        recyclerCategories.layoutManager = LinearLayoutManager(this@CategoryActivity, LinearLayout.VERTICAL, true)
        recyclerCategories.apply { layoutManager = GridLayoutManager(this@CategoryActivity, 2) }

        categoryAdapter = CategoryAdapter(it as ArrayList<CategoriesModelItem>, object : CategoryAdapter.ItemClickListener {
                override fun onIemClick(position: Int) {
                    var categoryId = it.get(position).id
                    openActivityList(position, categoryId, categoryContext)
                }
            }
        )
        recyclerCategories.adapter = categoryAdapter
    }


    @SuppressLint("ServiceCast")
    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun Fragment.hideKeyboard() {
        view?.let {
            activity?.hideKeyboard(it)
        }
    }

}

fun openActivityList(position : Int, categoryId : String, categoryContext : Context)
{
    val intent = Intent(categoryContext, ListActivity::class.java)
    intent.putExtra(Constants.KATEGORI_TASI, categoryId)
    categoryContext.startActivity(intent)
}
