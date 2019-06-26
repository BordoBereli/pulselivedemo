package com.kutluoglu.pulselivedemo.main.features.content

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kutluoglu.presentation.model.ContentView
import com.kutluoglu.pulselivedemo.R
import com.kutluoglu.pulselivedemo.utils.extensions.inflate

/**
 * Created by F.K. on 2019-06-25.
 *
 */

class ContentRvAdapter(
    private val contentListener: (ContentView, View) -> Unit
) : RecyclerView.Adapter<ContentItemHolder>() {

    private val contentList = mutableListOf<ContentView>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentItemHolder {
        return ContentItemHolder(parent.inflate(R.layout.content_item))
    }

    override fun getItemCount() = contentList.size

    override fun onBindViewHolder(holder: ContentItemHolder, position: Int) {
        holder.bindViewData(contentList[position], contentListener)
    }

    fun setRvData(list: List<ContentView>) {
        contentList.clear()
        contentList.addAll(list)
        notifyDataSetChanged()
    }
}