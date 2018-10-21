package com.qton.sophie.mojeelektrina

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.qton.sophie.mojeelektrina.utils.Prefs
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    lateinit var prefs: Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        prefs = Prefs(this)
        val money = prefs.monthlyPayment
        val notifs: Boolean = prefs.notifications
        val data: Boolean = prefs.shareData
        textBasic.setText(money.toString())
        checkBox.isChecked = notifs
        checkBox2.isChecked = data

        settings_activity_back.setOnClickListener {
            finish()
        }
    }
}
