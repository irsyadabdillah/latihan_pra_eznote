package com.irzstudio.latihanpraeznote.Screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.irzstudio.latihanpraeznote.Adapter.OnItemListener
import com.irzstudio.latihanpraeznote.Adapter.RecyclerAdapter
import com.irzstudio.latihanpraeznote.Data.Item
import com.irzstudio.latihanpraeznote.Data.ItemDatabase
import com.irzstudio.latihanpraeznote.HelperPref.Constant
import com.irzstudio.latihanpraeznote.HelperPref.PreferencesHelper
import com.irzstudio.latihanpraeznote.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var dataBase : ItemDatabase? = null
    private val dataItem : ArrayList<Item> = ArrayList()
    lateinit var adapterItem : RecyclerAdapter
    lateinit var sharedPref : PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataBase = ItemDatabase.getInstance(this)
        sharedPref = PreferencesHelper(this)

        setSupportActionBar(txt_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = sharedPref.getString(Constant.PREF_USERNAME)

        btn_save.setOnClickListener{
            saveItem()
            showDataAdapter()
        }

        showDataAdapter()

    }


    private fun saveItem(){
        //menyimpan data(item) di DB
        if (et_input.length() == 0) {
            Toast.makeText(applicationContext, "Silahkan ketikkan sesuatu", Toast.LENGTH_SHORT)
                .show()
        }else {
            val input = et_input.text.toString()
            val item = Item(nameitem = input)
            dataBase?.itemDao()?.insert(item)
        }
    }

    private  fun showDataAdapter(){
        adapterItem = RecyclerAdapter(dataItem)
        //untuk menghapus data agar yg sebelumnya tidak di tampilkan
        dataItem.clear()
        //menampilkan data di DB
        val dataFromDb = dataBase?.itemDao()?.getAll().orEmpty()
        dataItem.addAll(dataFromDb)

        /*val itemDummy = Item(nameitem = "Jeruk")
        val itemDummy2 = Item(nameitem = "Anggur")

        dataItem.add(itemDummy)
        dataItem.add(itemDummy2)
         */

        adapterItem.onClickListener = object  : OnItemListener {
            override fun onDelete(item: Item) {
                dataBase?.itemDao()?.delete(item)
                showDataAdapter()
            }
        }

        rv_recyclerview.adapter = adapterItem
        rv_recyclerview.layoutManager = LinearLayoutManager(this)

    }

    //menghubungkan appbar menu ke tampilan page
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.appbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteAllItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllItem () {
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Yes") { _, _ ->
            dataBase?.itemDao()?.deleteAll()
            Toast.makeText(applicationContext, "Berhasil menghapus semuanya", Toast.LENGTH_LONG)
                .show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Hapus Semuanya?")
        builder.setMessage("Apakah kamu yakin ingin menghapus semuanya?")
        builder.create().show()

    }

}