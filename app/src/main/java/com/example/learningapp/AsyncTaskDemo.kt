package com.example.learningapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

open class AsyncTaskDemo(val asyncDemo: AsyncDemo):Thread() {
    var mHandler = Handler(Looper.getMainLooper())
    override fun run() {
        mHandler.post {
            asyncDemo.onPreExecute()
        }
        var result = asyncDemo.runInBackGround()
        mHandler.post {
            asyncDemo.sentToMainThread(result)
        }
    }
}





/*
lateinit var mHandler : Handler
mHandler = object : Handler() {
            override fun handleMessage(msg: Message) {
                txt.text = msg.data.getString("abc" , "ok")
            }
        }



        inner class handlerThread() : Thread() {
        override fun run() {
            currentThread().name = "HandlerThread"
            val msg = Message.obtain()
            var bundle = Bundle()
            bundle.putString("abc", currentThread().name)
            msg.data = bundle
                mHandler.sendMessage(msg)
        }
    }
 */