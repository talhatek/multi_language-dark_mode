package com.example.nightmode

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*

@RequiresApi(17)
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        txt.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

    private fun setLocale(get: String?) {
        val locale = Locale("tr")
        Locale.setDefault(locale)
        val configuration = Configuration()
        configuration.setLocale(locale)
        baseContext.createConfigurationContext(configuration)
        recreate()
        //baseContext.resources.updateConfiguration(configuration,baseContext.resources.displayMetrics)
    }
}

