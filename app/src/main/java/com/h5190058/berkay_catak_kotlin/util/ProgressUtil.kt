package com.h5190058.berkay_catak_kotlin.util

import android.app.ProgressDialog
import android.content.Context

object ProgressUtil {
    fun progressOlustur(
        context: Context?,
        message: CharSequence?
    ): ProgressDialog? {
        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage(message)
        progressDialog.show()
        return progressDialog
    }
    fun progressKapat(progressDialog: ProgressDialog?) {
        if (progressDialog != null && progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }
}