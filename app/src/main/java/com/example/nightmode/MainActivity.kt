package com.example.nightmode

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.nightmode.utils.MyContextWrapper
import com.example.nightmode.utils.UserPreferences
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {
    private lateinit var xx: String
    private lateinit var userPreferences: UserPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switch_darkLight.setOnClickListener {
            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_YES ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Configuration.UI_MODE_NIGHT_NO ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
        txt.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
        en.setOnClickListener {
            setLocale("en")
            this@MainActivity.recreate()
        }
        tr.setOnClickListener {
            setLocale("tr")
            this@MainActivity.recreate()
        }


    }

    override fun attachBaseContext(newBase: Context?) {
        userPreferences = UserPreferences(newBase!!)
        xx = runBlocking { userPreferences.getLanguage.first() }
        super.attachBaseContext(MyContextWrapper.wrap(newBase, xx))


    }

    private fun setLocale(code: String) {

        runBlocking {
            userPreferences.saveLanguage(code)
        }

    }

}