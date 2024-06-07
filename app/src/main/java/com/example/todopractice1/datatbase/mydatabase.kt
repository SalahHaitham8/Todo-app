package com.example.todopractice1.datatbase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapppractice.database.task
import com.example.todoapppractice.database.tasks_dao

@Database([task::class], version = 1, exportSchema = true)
abstract class mydatabase:RoomDatabase(){
    abstract fun tasks_dao():tasks_dao

    companion object{
        private var instance:mydatabase?=null
        fun getinstance(context:Context): mydatabase {
            if (instance==null){
                instance=Room
                    .databaseBuilder(context.applicationContext,mydatabase::class.java,"mydatabase").allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build()

            }

                return instance!!


        }

    }


}