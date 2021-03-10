package com.omarmattr.mcc_firebase1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.omarmattr.mcc_firebase1.model.User
import com.omarmattr.mcc_firebase1.repository.MainActivityRepository
import com.omarmattr.mcc_firebase1.uitls.MyResult


class ViewModel(application: Application): AndroidViewModel(application) {
    private val repository= MainActivityRepository()
     val addUserLiveData: MutableLiveData<MyResult<Any>> = MutableLiveData()
     val getUserLiveData: MutableLiveData<MyResult<Any>> = MutableLiveData()
    fun addUser(user: User) {
        addUserLiveData.postValue(MyResult.loading(0))
           repository.addUser(user).addOnSuccessListener {
               addUserLiveData.postValue(  MyResult.success(user))
            }.addOnFailureListener {
               addUserLiveData.postValue(  MyResult.error(it.message,0))
            } }

    fun getAllUsers(){
        getUserLiveData.postValue(MyResult.loading(0))
        repository.getAllUsers().addSnapshotListener { value, error ->
            if (error==null){
                val array= arrayListOf<User>()
                if (value!=null)
                for (v in value.documents) array.add(v.toObject(User::class.java)!!)
                getUserLiveData.postValue(MyResult.success(array))
            }else  getUserLiveData.postValue(MyResult.error(error.message, 0))

        }
    }

}