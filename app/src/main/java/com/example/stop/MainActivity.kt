package com.example.stop

import android.graphics.drawable.TransitionDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.text.Layout
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Chronometer
import android.widget.ImageView
import android.widget.RelativeLayout

class MainActivity : AppCompatActivity() {
    private lateinit var btnstart:ImageView
    private lateinit var btnpause:ImageView
    private lateinit var btnreset:ImageView
    private lateinit var stopwatch:Chronometer
    var pauseAt:Long=0
    var start:Boolean=false
    var stop:Boolean=false
    var reset:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
        btnstart=findViewById(R.id.btnstart)
        btnpause=findViewById(R.id.btnpause)
        btnreset=findViewById(R.id.btnreset)
        stopwatch=findViewById(R.id.stopwatch)

        val mainContainer =findViewById<RelativeLayout>(R.id.mainContainer)
        val transition=mainContainer.background as TransitionDrawable

        btnstart.setOnClickListener(){
            stop=false
            reset=false
         if(start!=true){
             stopwatch.base=SystemClock.elapsedRealtime()-pauseAt;
             transition.startTransition(500)
             stopwatch.start()
             start=true
             btnpause.visibility= View.VISIBLE
             btnreset.visibility=View.VISIBLE
             btnstart.visibility=View.INVISIBLE
         }
        }

        btnpause.setOnClickListener(){
            start=false
            reset=false
            if(stop!=true) {
                pauseAt = SystemClock.elapsedRealtime() - stopwatch.base
                stopwatch.stop()
                stop=true
                btnpause.visibility= View.INVISIBLE
                btnreset.visibility=View.VISIBLE
                btnstart.visibility=View.VISIBLE
                transition.reverseTransition(600)
            }
        }

        btnreset.setOnClickListener(){
            start=false
            stop=false

           if(reset==false) {
               stopwatch.base = SystemClock.elapsedRealtime()
               stopwatch.stop()
               pauseAt = 0
               reset=true
               btnpause.visibility= View.INVISIBLE
               btnreset.visibility=View.INVISIBLE
               btnstart.visibility=View.VISIBLE
               transition.reverseTransition(600)
           }
        }
    }
}