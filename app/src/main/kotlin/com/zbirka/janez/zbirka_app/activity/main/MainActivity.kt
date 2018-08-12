package com.zbirka.janez.zbirka_app.activity.main

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.location.Location
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.widget.LinearLayout
import android.widget.Toast
import com.zbirka.janez.R
import com.zbirka.janez.application.ZbirkaApplication
import com.zbirka.janez.location.CurrentLocationListener
import com.zbirka.janez.model.WebData
import com.zbirka.janez.zbirka_app.loaders.UpdaterViewModel
import com.zbirka.janez.zbirka_app.loaders.WebDataViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnItemClickListener, TextWatcher {

    companion object {
        lateinit var component: MainComponent
    }

    private val webDataAdapter: WebDataAdapter = WebDataAdapter(this)
    @Inject
    lateinit var updaterViewModel: UpdaterViewModel
    @Inject
    lateinit var webDataViewModel: WebDataViewModel

    @Inject
    lateinit var currentLocationListener: CurrentLocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()

        DataBindingUtil.setContentView<com.zbirka.janez.databinding.ActivityMainBinding>(this, R.layout.activity_main).also {

            it.recyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                itemAnimator = DefaultItemAnimator()
                addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayout.VERTICAL))
                adapter = webDataAdapter
            }
            it.etInput.addTextChangedListener(this@MainActivity)
        }

        webDataViewModel.liveData.observe(this, Observer<List<WebData>> { t -> webDataAdapter.webDataList = t!! })

        currentLocationListener.observe(this, Observer<Location> { t ->
            Toast.makeText(this, "Location is:  ${if (t != null) "${t.latitude} ${t.longitude}" else "null"}", Toast.LENGTH_SHORT).show()
        })
    }

    fun inject() {
        component = DaggerMainComponent.builder()
                .zbirkaComponent(ZbirkaApplication.component)
                .mainModule(MainModule(this))
                .build()
        component.inject(this)

    }

    override fun itemClicked(webData: WebData) {
//        Log.e("click", "item click ${webData.id}")
    }

    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        updaterViewModel.setSharableString(s.toString())
    }

}