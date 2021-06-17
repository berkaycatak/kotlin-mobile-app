package com.h5190058.berkay_catak_kotlin.ui.login

import UsersModelItem
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedDispatcher
import androidx.lifecycle.Observer
import com.h5190058.berkay_catak_kotlin.R
import com.h5190058.berkay_catak_kotlin.ui.category.CategoryActivity
import com.h5190058.berkay_catak_kotlin.ui.list.ListActivity
import com.h5190058.berkay_catak_kotlin.util.Constants
import com.h5190058.berkay_catak_kotlin.util.ProgressUtil
import java.lang.Exception

class LoginActivity : AppCompatActivity() { 

    var usersViewModel: UsersViewModel?=null
    var userList = ArrayList<UsersModelItem>()
    private var progressDialog: ProgressDialog?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.editTextUsername)
        val password = findViewById<EditText>(R.id.editTextPassword)
        val button   = findViewById<Button>(R.id.login_button)
        button.setOnClickListener {
            try {
                progressDialog = ProgressUtil.progressOlustur(this@LoginActivity, resources.getString(R.string.progressMessage))

                val usernameText = username.text.toString()
                val passwordText = password.text.toString()

                if (usernameText == "" || passwordText == ""){
                    Toast.makeText(this@LoginActivity, resources.getString(R.string.loginErrorBosAlan) , Toast.LENGTH_SHORT).show()
                    ProgressUtil.progressKapat(progressDialog)
                }else{
                    getUsers(username.text.toString(), password.text.toString())
                }

            }catch (e : Exception){
                Log.d("hata", e.toString())
            }
        }
    }


    fun getUsers(username : String, password : String){

        usersViewModel =  UsersViewModel();
        usersViewModel?.apply {

            usersLiveData?.observe(this@LoginActivity, Observer {

                it.run {
                    checkUser(it, username, password)
                }
            })

            error?.observe(this@LoginActivity, Observer {

                it.run {
                    Toast.makeText(applicationContext,this.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })

            loading?.observe(this@LoginActivity, Observer {
                //Handle loading
            })
        }

    }

    private fun checkUser(
        it: List<UsersModelItem>?,
        username: String,
        password: String
    ): Int {
        userList = it as ArrayList<UsersModelItem>

        var sayac = 0;
        var buldu = 0;
        for (usersModel: UsersModelItem in userList) {

            if (userList.get(sayac).username == username && userList.get(sayac).password == password) {
                ProgressUtil.progressKapat(progressDialog)
                startCategoryActivity(sayac)
                buldu = 1;
            }
            sayac++;
        }
        if (buldu == 0){
            ProgressUtil.progressKapat(progressDialog)
            Toast.makeText(this@LoginActivity, resources.getString(R.string.loginError) , Toast.LENGTH_SHORT).show()
        }

        return Log.d("data", "bulunamadı")
    }

    private fun startCategoryActivity(sayac: Int) {
        var intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra(Constants.USER_NAME, userList.get(sayac).name)
        intent.putExtra(Constants.USER_PHOTO, userList.get(sayac).pp)
        startActivity(intent)
        finish()
    }

}
