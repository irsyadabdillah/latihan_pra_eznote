package com.irzstudio.latihanpraeznote.Screen

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.irzstudio.latihanpraeznote.Adapter.RecyclerAdapter
import com.irzstudio.latihanpraeznote.Data.Item
import com.irzstudio.latihanpraeznote.HelperPref.Constant
import com.irzstudio.latihanpraeznote.HelperPref.PreferencesHelper
import com.irzstudio.latihanpraeznote.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val dataItem : ArrayList<Item> = ArrayList()

    lateinit var adapterItem : RecyclerAdapter

    lateinit var sharedPref : PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref = PreferencesHelper(this)

        setSupportActionBar(txt_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = sharedPref.getString(Constant.PREF_USERNAME)

        showDataAdapter()


    }


    private  fun showDataAdapter(){
        adapterItem = RecyclerAdapter(dataItem)

        val itemDummy = Item(nameitem = "Jeruk")
        val itemDummy2 = Item(nameitem = "Anggur")

        dataItem.add(itemDummy)
        dataItem.add(itemDummy2)

        rv_recyclerview.adapter = adapterItem
        rv_recyclerview.layoutManager = LinearLayoutManager(this)

    }


}