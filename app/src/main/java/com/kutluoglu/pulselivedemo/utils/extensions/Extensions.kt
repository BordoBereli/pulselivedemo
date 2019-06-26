package com.kutluoglu.pulselivedemo.utils.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.kutluoglu.pulselivedemo.base.BaseFragment
import com.kutluoglu.pulselivedemo.utils.ProgressSpinner
import kotlinx.android.synthetic.main.app_bar_main.view.*

/**
 * Created by F.K. on 2019-06-25.
 *
 */

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachtoRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachtoRoot)
}

/**
 * Toolbar Extentions
 */

fun Toolbar.setPLTitle(title: String) { toolbar.title= title }

/**
 * Show Spinner Progress Dialog
 */
fun BaseFragment.showProgressSpinner(): AlertDialog = ProgressSpinner(
    this.context!!
).create()

/**
 * ImageView to set Drawable by Resource ID
 */

fun ImageView.setImageWithUrl(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .centerCrop()
        .into(this)
}

/**
 * Set any View visible or gone state
 */

fun View.visibileOrGone(visible: Boolean) {
    visibility = if(visible) View.VISIBLE else View.GONE
}

/**
 * Dialog Extensions
 */

inline fun Context.showDialog(cancelable: Boolean = true, cancelableTouchOutSide: Boolean = true,
                              builderFunction: AlertDialog.Builder.() -> Any) {
    val builder = AlertDialog.Builder(this)
    builder.builderFunction()

    val dialog = builder.create()

    dialog.setCancelable(cancelable)
    dialog.setCanceledOnTouchOutside(cancelableTouchOutSide)

    dialog.show()
}

inline fun AlertDialog.Builder.positiveButton(text: String = "Okay", crossinline handleClick: (i: Int) -> Unit = {}) {
    this.setPositiveButton(text) { _, i -> handleClick(i) }
}




