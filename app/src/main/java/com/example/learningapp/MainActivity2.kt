package com.example.learningapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message

class MainActivity2 : AppCompatActivity() {

    lateinit var mHandler : Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        mHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {

            }
        }

    }

    inner class handlerThread() : Thread() {
        override fun run() {
            currentThread().name = "HandlerThread"
            val msg = Message.obtain()
            var bundle = Bundle()
            bundle.putString("abc", currentThread().name )
            msg.data = bundle
            mHandler.sendMessage(msg)
        }
    }
}
