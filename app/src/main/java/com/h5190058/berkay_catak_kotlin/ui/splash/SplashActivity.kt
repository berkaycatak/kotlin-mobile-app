package com.h5190058.berkay_catak_kotlin.ui.splash

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.h5190058.berkay_catak_kotlin.R
import com.h5190058.berkay_catak_kotlin.ui.category.CategoryActivity
import com.h5190058.berkay_catak_kotlin.ui.login.LoginActivity
import com.h5190058.berkay_catak_kotlin.util.AlertUtil

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        init()

    }

    private fun init() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({
            if (isOnline(applicationContext)) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {

                // AlertDialog nesnemizi üretiyoruz
                val alert = AlertDialog.Builder(this)

                // Başlık
                alert.setTitle(resources.getString(R.string.internetYok))
                //Mesaj
                alert.setMessage(resources.getString(R.string.internetYokMesaj))
                //Geri tuşununu da pasif hale getiriyoruz
                alert.setCancelable(false);

                alert.setPositiveButton(resources.getString(R.string.internetYokAButon)) { dialogInterface: DialogInterface, i: Int ->
                    // Evet butonuna tıklayınca olacaklar
                    val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                    startActivity(intent)

                }

                alert.setNegativeButton(resources.getString(R.string.internetYokPButon)) {dialogInterface: DialogInterface, i: Int ->
                    // Hayır butonuna tıklayınca olacaklar
                    finish()
                }
                alert.show()

            }
        }, 3000) // 3000
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

}