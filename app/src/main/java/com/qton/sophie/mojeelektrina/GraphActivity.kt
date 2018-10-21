package com.qton.sophie.mojeelektrina

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.RadioGroup
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint
import com.qton.sophie.mojeelektrina.utils.Constants.*
import kotlinx.android.synthetic.main.activity_graph.*

class GraphActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)
        //setupGrapgs
        if (graphDayMe.isEmpty) {
            graphDayMe = createGraph(24)
            graphDayTown = createGraph(24)
            graphMonthMe = createGraph(30)
            graphMonthTown = createGraph(30)
            graphWeekMe = createGraph(7)
            graphWeekTown = createGraph(7)
            graphYearMe = createGraph(12)
            graphYearTown = createGraph(12)
        }

        graph_activity_den.isChecked = (true)
        graph_activity_my_graph.isChecked = true
        setupGraphs(graphDayMe, null, getChoice())

        graph_activity_back.setOnClickListener {
            finish()
        }
        transparentToolbar()

        graph_activity_my_graph.setOnCheckedChangeListener { _, _ ->
            updateGraphs()
        }
        graph_activity_average_graph.setOnCheckedChangeListener { _, _ ->
            updateGraphs()
        }
        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
                // This will get the radiobutton that has changed in its check state
                updateGraphs()

            }
        })
    }

    private fun setupGraphs(series1: BarGraphSeries<DataPoint>?, series2: BarGraphSeries<DataPoint>?, lastNumber: Int) {
        graph_activity_graph.removeAllSeries()
        val graph = graph_activity_graph
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0.0);
        graph.getViewport().setMaxX(1.0 * lastNumber);
        if (series1 != null) {
            graph_activity_graph.addSeries(series1)
        }
        if (series2 != null) {
            series2.color = R.color.green
            graph_activity_graph.addSeries(series2)
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

    fun getChoice(): Int {
        if (graph_activity_den.isChecked) {
            return 24
        }
        if (graph_activity_tyden.isChecked) {
            return 7
        }
        if (graph_activity_mesic.isChecked) {
            return 30
        }
        if (graph_activity_rok.isChecked) {
            return (Math.random() * 10).toInt()
        } else return 24
    }

    fun createGraph(lastNumber: Int): BarGraphSeries<DataPoint> {
        var series: BarGraphSeries<DataPoint> = BarGraphSeries();
        for (i in 0..lastNumber) {
            var x = i.toDouble();
            var y = Math.random() * 1.6
            series.appendData(DataPoint(x, y), false, 50)
        }
        series.dataWidth = 0.5
        return series
    }

    fun updateGraphs() {
        when (getChoice()) {
            //den
            24 -> {
                //muj graf ano
                if (graph_activity_my_graph.isChecked) {
                    //prumer taky ano
                    if (graph_activity_average_graph.isChecked) {
                        setupGraphs(graphDayMe, graphDayTown, 24)
                    } else {
                        //prumer ne
                        setupGraphs(graphDayMe, null, 24)
                    }
                } else if (graph_activity_average_graph.isChecked) {
                    setupGraphs(null, graphDayTown, 24)
                } else {
                    setupGraphs(null, null, 24)
                }
            }
            7 -> {
                //muj graf ano
                if (graph_activity_my_graph.isChecked) {
                    //prumer taky ano
                    if (graph_activity_average_graph.isChecked) {
                        setupGraphs(graphWeekMe, graphWeekTown, 7)
                    } else {
                        //prumer ne
                        setupGraphs(graphWeekMe, null, 7)
                    }
                } else if (graph_activity_average_graph.isChecked) {
                    setupGraphs(null, graphWeekTown, 7)
                } else {
                    setupGraphs(null, null, 7)
                }
            }
            12 -> {
                //muj graf ano
                if (graph_activity_my_graph.isChecked) {
                    //prumer taky ano
                    if (graph_activity_average_graph.isChecked) {
                        setupGraphs(graphYearMe, graphYearTown, 24)
                    } else {
                        //prumer ne
                        setupGraphs(graphYearMe, null, 24)
                    }
                } else if (graph_activity_average_graph.isChecked) {
                    setupGraphs(null, graphYearTown, 24)
                } else {
                    setupGraphs(null, null, 24)
                }
            }
            30 -> {
                //muj graf ano
                if (graph_activity_my_graph.isChecked) {
                    //prumer taky ano
                    if (graph_activity_average_graph.isChecked) {
                        setupGraphs(graphMonthMe, graphMonthTown, 24)
                    } else {
                        //prumer ne
                        setupGraphs(graphMonthMe, null, 24)
                    }
                } else if (graph_activity_average_graph.isChecked) {
                    setupGraphs(null, graphMonthTown, 24)
                } else {
                    setupGraphs(null, null, 24)
                }
            }
        }
    }
}
