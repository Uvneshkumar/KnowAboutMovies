package com.bigbrains.knowaboutmovies.common.views

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bigbrains.knowaboutmovies.R

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    private lateinit var mContext: Context
    private var progressDialog: AlertDialog? = null
    private var errorMessageDialog: android.app.AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
    }

    fun showProgress(msg: String = "Loading...") {
        if (progressDialog != null) {
            return
        } else if (isFinishing) {
            return
        }
        dismissKeyBoard()
        val builder = AlertDialog.Builder(mContext)
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_progress_dialog, null)
        view.findViewById<TextView>(R.id.progress_text).text = msg
        builder.setView(view)
        progressDialog = builder.create()
        progressDialog?.show()
        progressDialog?.setCancelable(false)
    }

    fun hideProgress() {
        if (progressDialog != null && progressDialog?.isShowing == true && !isFinishing) {
            progressDialog?.dismiss()
            progressDialog = null
        }
    }

    private fun dismissKeyBoard(windowToken: IBinder) {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(windowToken, 0)
        } catch (e: Exception) {
        }
    }

    fun dismissKeyBoard() {
        try {
            dismissKeyBoard(findViewById<View>(android.R.id.content).windowToken)
        } catch (e: Exception) {
        }
    }

    fun showErrorDialog(
        errorMsg: String,
        onAction: () -> Unit = {},
        showCancelButton: Boolean = false,
        onCancel: () -> Unit = {}
    ) {
        dismissKeyBoard()
        val build = android.app.AlertDialog.Builder(this).apply {
            setMessage(errorMsg)
            setPositiveButton(R.string.action_ok) { _, _ ->
                onAction()
            }
            if (showCancelButton) {
                setNegativeButton(R.string.action_cancel) { _, _ ->
                    onCancel()
                }
            }
        }
        errorMessageDialog = build.create()
        errorMessageDialog?.setCanceledOnTouchOutside(false)
        if (!isFinishing) {
            errorMessageDialog?.show()
        }
    }

}