package com.kutluoglu.pulselivedemo.main.features.content

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kutluoglu.presentation.model.ContentView
import com.kutluoglu.pulselivedemo.R

/**
 * Created by F.K. on 2019-06-25.
 *
 */

open class ContentItemHolder(private val contentItem: View) : RecyclerView.ViewHolder(contentItem) {
    private val title: TextView = contentItem.findViewById(R.id.content_title)
    private val subTitle: TextView = contentItem.findViewById(R.id.content_subtitle)
    private val date: TextView = contentItem.findViewById(R.id.content_date)

    fun bindViewData(content: ContentView, contentListener:(ContentView, View) -> Unit) {
        content.title.let { title.text = it }
        content.subtitle.let { subTitle.text = it }
        content.date.let { date.text = it }
        contentItem.setOnClickListener { contentListener(content, contentItem) }
    }
}