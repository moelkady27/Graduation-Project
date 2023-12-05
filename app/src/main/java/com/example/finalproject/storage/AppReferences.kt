package com.example.finalproject.storage

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

object AppReferences {

    fun setLoginState(context: Activity?, state: Boolean) {
        val sharedPreferences: SharedPreferences =
            context!!.getSharedPreferences("login", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("login", state)
        editor.apply()
    }

    fun getLoginState(context: Activity?): Boolean {
        val sharedPreferences: SharedPreferences =
            context!!.getSharedPreferences("login", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("login", false)
    }

    fun setUserId(context: Activity?, userId: String) {
        val sharedPreferences: SharedPreferences =
            context!!.getSharedPreferences("user", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("userId", userId)
        editor.apply()
    }

    fun getUserId(context: Activity?): String {
        val sharedPreferences: SharedPreferences =
            context!!.getSharedPreferences("user", Context.MODE_PRIVATE)
        return sharedPreferences.getString("userId", "")!!
    }

}