package com.example.todoapppractice.database

import androidx.room.*

@Dao
interface tasks_dao{
    @Insert
    fun insert_task(task: task)
    @Delete
    fun delete_task(task: task)
    @Update
    fun Update_task(task: task)
    @Query("select * from task")
    fun getalltasks():List<task>
@Query("select * from TASK where datetime= :datetime")
    fun GettaskByDate(datetime:Long):List<task>




}