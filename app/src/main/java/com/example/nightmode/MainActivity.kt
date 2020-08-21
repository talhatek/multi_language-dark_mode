package com.example.nightmode

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.nightmode.utils.MyContextWrapper
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var xx: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*  val preferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
          val xx = preferences.getString("myLang", "tr")
          setLocale(xx!!)*/
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
        val sharedPreferences =
            androidx.preference.PreferenceManager.getDefaultSharedPreferences(newBase)
        xx = sharedPreferences.getString("myLang", "tr").toString()
        super.attachBaseContext(MyContextWrapper.wrap(newBase, xx))

    }

    private fun setLocale(code: String) {

        /*  val locale = Locale(code)
          Locale.setDefault(locale)
          val configuration = Configuration()
          configuration.setLocale(locale)
          baseContext.resources.updateConfiguration(configuration,baseContext.resources.displayMetrics)
         val configuration = baseContext.resources.configuration
         val locale = Locale("tr")
         configuration.setLocale(locale)
         val context=createConfigurationContext(configuration)
         val res=context.resources
        val preferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString("myLang", code)
        editor.apply()*/
        val pref =
            androidx.preference.PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
        val edit = pref.edit()
        edit.putString("myLang", code)
        edit.apply()
    }

}