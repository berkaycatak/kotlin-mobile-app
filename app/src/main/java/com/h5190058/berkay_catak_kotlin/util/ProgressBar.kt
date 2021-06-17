package com.h5190058.berkay_catak_kotlin.util

import android.R
import android.app.Dialog
import android.os.AsyncTask
import com.h5190058.berkay_catak_kotlin.ui.login.LoginActivity

class ProgressBar (var activity: LoginActivity) : AsyncTask<Void, Void, Void>(){

    var dialog = Dialog(activity)

    override fun onPreExecute() {
        val view = activity.layoutInflater.inflate(com.h5190058.berkay_catak_kotlin.R.layout.full_screen_progress_bar,null)
        dialog.setContentView(view)
        dialog.setCancelable(false)
        dialog.show()
        super.onPreExecute()
    }

    override fun doInBackground(vararg p0: Void?): Void? {
        Thread.sleep(400)
        return null
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        dialog.dismiss()
    }

}