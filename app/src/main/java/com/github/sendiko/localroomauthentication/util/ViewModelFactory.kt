package com.github.sendiko.localroomauthentication.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.sendiko.localroomauthentication.database.LocalRoomDatabase
import com.github.sendiko.localroomauthentication.login.LoginViewModel

class ViewModelFactory(
    private val context: Context
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = LocalRoomDatabase.getInstance(context).userDao
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}