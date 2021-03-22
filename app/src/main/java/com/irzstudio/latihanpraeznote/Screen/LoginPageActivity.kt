package com.irzstudio.latihanpraeznote.Screen


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.irzstudio.latihanpraeznote.HelperPref.Constant
import com.irzstudio.latihanpraeznote.HelperPref.PreferencesHelper
import com.irzstudio.latihanpraeznote.R
import kotlinx.android.synthetic.main.login_page.*

class LoginPageActivity : AppCompatActivity() {

    lateinit var sharePref : PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        sharePref = PreferencesHelper(this)

        btn_next.setOnClickListener {
            if (et_username.text.isNotEmpty()){
                sharePref.put(Constant.PREF_USERNAME, et_username.text.toString())
                sharePref.put(Constant.PREF_IS_LOGIN, true)
                Toast.makeText(applicationContext, "Berhasil Masuk", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        if (sharePref.getBoolean( Constant.PREF_IS_LOGIN) == true){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


}



