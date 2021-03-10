package com.omarmattr.mcc_firebase1.repository

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.omarmattr.mcc_firebase1.model.User

class MainActivityRepository private constructor() {
    private val db by lazy {
        FirebaseFirestore.getInstance()
    }
     fun addUser(user: User)=db.collection("user").add(user)
    fun getAllUsers()=db.collection("user")
    companion object {
        @Volatile
        private var instance: MainActivityRepository? = null
        private val LOCK = Any()

        operator fun invoke() =
            instance ?: synchronized(LOCK) {
                instance ?: createMainActivityRepository().also {
                    instance = it
                }
            }

        private fun createMainActivityRepository()= MainActivityRepository()
    }
}