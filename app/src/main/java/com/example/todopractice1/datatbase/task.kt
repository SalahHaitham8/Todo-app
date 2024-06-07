package com.example.todoapppractice.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class task (
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    var title:String?=null,
    var describtion:String?=null,
    val datetime:Long?=null,
    var isdone:Boolean=false




):Serializable