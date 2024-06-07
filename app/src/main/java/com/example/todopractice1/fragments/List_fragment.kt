package com.example.todopractice1.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapppractice.database.task
import com.example.todopractice1.DialogFragment
import com.example.todopractice1.EditTask
import com.example.todopractice1.databinding.FragmentListFragment2Binding
import com.example.todopractice1.datatbase.mydatabase
import com.example.todopractice1.task_adapter
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.*


class List_fragment : DialogFragment() {
    lateinit var viewbinding:FragmentListFragment2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       viewbinding= FragmentListFragment2Binding.inflate(layoutInflater,container,false)
        return viewbinding.root
        

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
   initviews()

    }
 var adapter=task_adapter(null)
    val calender=Calendar.getInstance()
    init {
        calender.set(Calendar.HOUR_OF_DAY,0)
        calender.set(Calendar.MINUTE,0)
        calender.set(Calendar.SECOND,0)
        calender.set(Calendar.MILLISECOND,0)
    }
    private fun initviews() {
        viewbinding.recyclerView.adapter=adapter
     adapter.onitemDeleted=task_adapter.onitemclicklistner{
         task, position ->  deleteTaskfromDB(task,position)
     }

        adapter.onitemclick=object:task_adapter.onitemclicklisner{
            override fun onitemclick(task: task, position: Int) {
             showmessage("what do you want?",
                 " Update",
                 { _, dialog->updatetask(task) },

                 "Make done! ",
                 { _,dialog->makedone(task) })
      }
        }


        viewbinding.calendarView.setSelectedDate(
            CalendarDay.today()
        )
      viewbinding.calendarView.setOnDateChangedListener{
          widget,date,selected->
          if (selected){
              calender.set(Calendar.YEAR,date.year)
              calender.set(Calendar.MONTH,date.month-1)
               calender.set(Calendar.DAY_OF_MONTH,date.day)
              loadtasks()
          }

      }



    }
    fun makedone(task: task){

        task.isdone=true
        mydatabase.getinstance(requireContext()).tasks_dao().Update_task(task)
        loadtasks()

    }
    fun updatetask(task: task){
   val intent=Intent(requireContext(),EditTask::class.java)
         intent.putExtra("task",task)
        startActivity(intent)
    }

    private fun deleteTaskfromDB(task: task, position: Int) {
          mydatabase.getinstance(requireContext()).tasks_dao().delete_task(task)
        adapter.deleteview(task,position)
    }


    override fun onStart() {
        super.onStart()
        loadtasks()

    }

     fun loadtasks() {
         context?.let {
             val tasks= mydatabase.getinstance(it)
                 .tasks_dao()
                 .GettaskByDate(calender.timeInMillis)
             adapter.bindtask(tasks.toMutableList())
         }

    }


/*
    private var taskadapter=task_adapter(emptyList())
    private fun initviews() {
        viewbinding.recycler.adapter=taskadapter
    }

    private fun loadtasks() {
        var tasks=mydatabase.getinstance(requireContext()).tasks_dao().getalltasks()
        taskadapter.loadtasks(tasks)


    }
*/
}
