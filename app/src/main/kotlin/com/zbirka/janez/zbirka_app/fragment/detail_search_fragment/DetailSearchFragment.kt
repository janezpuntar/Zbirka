package com.zbirka.janez.zbirka_app.fragment.detail_search_fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zbirka.janez.R
import com.zbirka.janez.model.SearchItem
import com.zbirka.janez.zbirka_app.activity.main.MainActivity
import com.zbirka.janez.zbirka_app.loaders.UpdaterViewModel

class DetailSearchFragment : Fragment() {

    private lateinit var updaterViewModel: UpdaterViewModel
    private val searchItem: SearchItem = SearchItem()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        updaterViewModel = ViewModelProviders.of(activity!!)[UpdaterViewModel::class.java]
        updaterViewModel.sharableString.observe(this, Observer<String> {
            t -> searchItem.searchString = t!!
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            DataBindingUtil.inflate<com.zbirka.janez.databinding.DetailSearchFragmentBinding>(inflater, R.layout.detail_search_fragment, container, false).also {
                it.searchItem = searchItem
            }.root
}