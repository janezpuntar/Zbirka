package com.zbirka.janez.zbirka_app.activity.main

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.zbirka.janez.R
import com.zbirka.janez.databinding.WebDataRowItemBinding
import com.zbirka.janez.model.WebData

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String) {
    Picasso.get().load(url).fit().centerCrop().into(this)
}

interface OnItemClickListener {

    fun itemClicked(webData: WebData)
}

class WebDataClickHandlers(private val listener: OnItemClickListener) {
    fun onTitleClicked(view: View, webData: WebData) {
        listener.itemClicked(webData)
    }

    fun onBodyClicked(webData: WebData) {
        listener.itemClicked(webData)
    }
}


class WebDataViewHolder(private val webDataRowItemBinding: com.zbirka.janez.databinding.WebDataRowItemBinding,
                        onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(webDataRowItemBinding.root) {

    init {
        this.webDataRowItemBinding.handlers = WebDataClickHandlers(onItemClickListener)
    }

    fun setWebData(webData: WebData) {
        webDataRowItemBinding.webData = webData
    }
}

class WebDataAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<WebDataViewHolder>() {

    var webDataList: List<WebData> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): WebDataViewHolder {

        val binding = DataBindingUtil.inflate<WebDataRowItemBinding>(
                LayoutInflater.from(viewGroup.context), R.layout.web_data_row_item, viewGroup, false)

        return WebDataViewHolder(binding, listener)
    }

    override fun onBindViewHolder(webDataViewHolder: WebDataViewHolder, i: Int) {
        webDataViewHolder.setWebData(webDataList[i])
    }

    override fun getItemCount(): Int {
        return webDataList.size
    }
}