package com.example.rickyandmorty_michaelzhong.util

import android.util.Log

class Logger {

    companion object{
        private val DEBUG_TAG = "TAG_DEBUG"
        private val ERROR_TAG = "TAG_ERROR"

        fun logDebug(message: String){
            Log.d(DEBUG_TAG, message)
        }
        fun logError(error: String){
            Log.e(ERROR_TAG, error)
        }
    }
}