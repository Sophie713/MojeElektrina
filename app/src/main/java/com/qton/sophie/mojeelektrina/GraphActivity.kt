package com.qton.sophie.mojeelektrina

import android.app.Activity
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.RadioButton
import android.widget.RadioGroup
import com.jjoe64.graphview.ValueDependentColor
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_graph.*

class GraphActivity : AppCompatActivity() {

    var series: BarGraphSeries<DataPoint> = BarGraphSeries();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

        graph_activity_back.setOnClickListener {
            finish()
        }
        transparentToolbar()
        createGraph()
        radioGroup.setOnCheckedChangeListener(object:RadioGroup.OnCheckedChangeListener {
                override fun onCheckedChanged(group:RadioGroup, checkedId:Int) {
                    // This will get the radiobutton that has changed in its check state
                    createGraph()
                }
            })


    }

    private fun transparentToolbar() {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21)
        {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        }
        if (Build.VERSION.SDK_INT >= 21)
        {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            getWindow().setStatusBarColor(Color.TRANSPARENT)
        }
    }
    private fun setWindowFlag(activity: Activity, bits:Int, on:Boolean) {
        val win = activity.getWindow()
        val winParams = win.getAttributes()
        if (on)
        {
            winParams.flags = winParams.flags or bits
        }
        else
        {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.setAttributes(winParams)
    }
    fun getChoice(): Int{
        if(graph_activity_den.isChecked){
            return 24
        }
        if(graph_activity_tyden.isChecked){
            return 7
        }
        if(graph_activity_mesic.isChecked){
            return 30
        }
        if(graph_activity_rok.isChecked){
            return (Math.random()*10).toInt()
        }
        else return 12
    }
    fun createGraph(){
        series = BarGraphSeries()
        graph_activity_graph.removeAllSeries()
        val last= getChoice()
        for(i in 0..last){
            var x = i.toDouble();
            var y = Math.random()*1.6
            y = String.format("%.2f", y).toDouble()

            series.appendData(DataPoint(x, y), false, 50)
        }
        series.dataWidth = 0.5
        graph_activity_graph.addSeries(series)
    }
}
