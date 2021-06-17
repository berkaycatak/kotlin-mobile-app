package com.h5190058.berkay_catak_kotlin.ui.detail

import AdsModelItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.h5190058.berkay_catak_kotlin.R
import com.h5190058.berkay_catak_kotlin.util.Constants
import com.h5190058.berkay_catak_kotlin.util.ObjectUtil
import com.h5190058.berkay_catak_kotlin.util.getImage

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val getStringAd = intent.getStringExtra(Constants.ILAN_TASI)
        val ad : AdsModelItem = ObjectUtil.jsonStringToObject(getStringAd.toString())

        initValues(ad)
    }

    private fun initValues(ad: AdsModelItem) {
        val user_name = findViewById(R.id.txtViewKullaniciTamad) as TextView
        val user_pp = findViewById(R.id.imageViewProfilFoto) as ImageView
        val adImage = findViewById(R.id.imageViewIlanFoto) as ImageView
        val categoryName = findViewById(R.id.txtViewKategori) as TextView
        val title = findViewById(R.id.txtViewBaslik) as TextView
        val article = findViewById(R.id.txtViewAciklama) as TextView
        val detail1 = findViewById(R.id.txtViewDetay1) as TextView
        val detail2 = findViewById(R.id.txtViewDetay2) as TextView
        val detail3 = findViewById(R.id.txtViewDetay3) as TextView

        user_name.text = ad.kullanicikadi
        adImage.getImage(ad.postres)
        user_pp.getImage(ad.kullanicipp)
        categoryName.text = ad.kategoriad
        title.text = ad.baslik
        article.text = ad.icerik
        detail1.text = ad.detay1
        detail2.text = ad.detay2
        detail3.text = ad.detay3
    }
}