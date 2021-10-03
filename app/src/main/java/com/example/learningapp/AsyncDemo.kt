package com.example.learningapp

import android.os.Handler

interface AsyncDemo {
    fun onPreExecute()
    fun runInBackGround():String
    fun sentToMainThread(str : String)
}