package com.qton.sophie.mojeelektrina

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isGood: Boolean = true
    val sad: String = "Koťátko pláče, protože jednáš neúsporně se svou energií."
    val happy: String = "Gratulujeme, dneska jste obci pomohli zachránit 60kWh. \nTo odpovídá energii, kterou byste vytvořili kdybyste jeli na kole 600 hodin."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transparentToolbar()
        main_activity_top_info_text.text = happy
        main_activity_price.setOnClickListener {
            if (isGood) {
                main_activity_top_info_text.text = sad
                main_activity_kitty.setImageResource(R.drawable.kitty_cry)
                isGood = false
            } else {
                main_activity_top_info_text.text = happy
                main_activity_kitty.setImageResource(R.drawable.kitty_bicycle)
                isGood = true
            }
        }

        main_activity_info_button.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }

        main_activity_statistics_button.setOnClickListener {
            val intent = Intent(this, GraphActivity::class.java)
            startActivity(intent)
        }

        main_activity_settings_button.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun transparentToolbar() {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            getWindow().setStatusBarColor(Color.TRANSPARENT)
        }
    }

    private fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
        val win = activity.getWindow()
        val winParams = win.getAttributes()
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.setAttributes(winParams)
    }
}
