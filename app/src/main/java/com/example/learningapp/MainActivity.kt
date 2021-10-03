package com.example.learningapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import java.io.PipedReader
import java.io.PipedWriter
import java.lang.Thread.currentThread
import java.lang.Thread.sleep


class MainActivity : AppCompatActivity() {
    lateinit var but : Button
    lateinit var txt : TextView
    lateinit var progBar : ProgressBar
    lateinit var mHandler : Handler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       but = findViewById(R.id.button)
        txt = findViewById(R.id.txt)
        progBar = findViewById(R.id.progBar)

        mHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                txt.text = msg.data.getInt("key",55).toString()
                if(msg.data.getInt("key")==9){
                    progBar.visibility = View.INVISIBLE
                }
            }
        }

        var asyncTaskDemo = AsyncTaskDemo(object : AsyncDemo{
            override fun onPreExecute() {
                progBar.visibility = View.VISIBLE
            }

            override fun runInBackGround(): String {
                sleep(3000)
                return currentThread().name.toString()
            }

            override fun sentToMainThread(str: String) {
                progBar.visibility = View.INVISIBLE
                txt.text = str
                Log.d("abc", currentThread().name.toString())
            }

        })


        but.setOnClickListener {
//            progBar.visibility = View.VISIBLE
//            val doInBackGround = DoInBackground(5)
//            doInBackGround.start()
            var asyncTaskDemo = AsyncTaskDemo(object : AsyncDemo{
                override fun onPreExecute() {
                    progBar.visibility = View.VISIBLE
                }

                override fun runInBackGround(): String {
                    sleep(3000)
                    return currentThread().name.toString()
                }

                override fun sentToMainThread(str: String) {
                    progBar.visibility = View.INVISIBLE
                    txt.text = str
                    Log.d("abc", currentThread().name.toString())
                }

            })
            asyncTaskDemo.start()
        }

    }



    inner class DoInBackground(a:Int) : Thread() {
        var num = a
        override fun run() {
            Looper.prepare()
            repeat(5){
                val msg = Message.obtain()
                var bundle = Bundle()
                bundle.putInt("key",it+num)
                msg.data = bundle
                mHandler.sendMessage(msg)
                sleep(1000)
            }
            Looper.loop()
        }

    }

}









/*
https://avatars3.githubusercontent.com/u/14994036?s=400&u=2832879700f03d4b37ae1c09645352a352b9d2d0&v=4
 */
















/*
class ok : AsyncTask<Int,Int,Int>(){
        override fun doInBackground(vararg params: Int?): Int {
            TODO("Not yet implemented")
        }

    }
 */