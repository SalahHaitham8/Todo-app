package com.example.todopractice1.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.os.IInterface
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.todoapppractice.database.task
import com.example.todoapppractice.database.tasks_dao
import com.example.todopractice1.databinding.FragmentAddtaskFragmentBinding
import com.example.todopractice1.datatbase.mydatabase
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*


class addtask_fragment :BottomSheetDialogFragment(){
    lateinit var viewbinding:FragmentAddtaskFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewbinding=FragmentAddtaskFragmentBinding.inflate(layoutInflater,container,false)
        return viewbinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewbinding.btnAddtask.setOnClickListener{
            createtask()
        }
        viewbinding.taskDate.setOnClickListener{
            showdatepickerDialog()
        }


    }
val calender=Calendar.getInstance()
    private fun showdatepickerDialog() {
        context?.let {
            var dialog = DatePickerDialog(it)
            dialog.setOnDateSetListener {datepicker, year, month, day ->
                viewbinding.taskDate.setText("$year-${month+1}-$day")
                calender.set(year,month,day)
                calender.set(Calendar.HOUR_OF_DAY,0)
                calender.set(Calendar.MINUTE,0)
                calender.set(Calendar.SECOND,0)
                calender.set(Calendar.MILLISECOND,0)
                      }
            dialog.show()
        }
    }



    private fun valid(): Boolean {
        var isvalid=true
        if (viewbinding.title.text.toString().isNullOrBlank()) {
                viewbinding.titleContainer.error="please enter title"
                isvalid=false}
        else{
            viewbinding.titleContainer.error=null
        }
        if (viewbinding.describtion.text.toString().isNullOrBlank()) {
            viewbinding.describtionContainer.error="please enter describtion"
            isvalid=false}
        else{
            viewbinding.describtionContainer.error=null
        }
        if (viewbinding.taskDate.text.toString().isNullOrBlank()) {
            viewbinding.dateContainer.error="please choose date"
            isvalid=false}
        else{
            viewbinding.dateContainer.error=null
        }


return isvalid
    }
    private fun createtask() {
        if (!valid()){
            return

        }
        var task=task(
            title = viewbinding.title.text.toString(),
            describtion = viewbinding.describtion.text.toString(),
            datetime = calender.timeInMillis



        )
        mydatabase.getinstance(requireContext())
            .tasks_dao()
            .insert_task(task)
        Ontaskaddedlistner?.ontaskadded()
             dismiss()

    }
    var Ontaskaddedlistner:ontaskaddedlistner?=null
fun interface ontaskaddedlistner{
    fun ontaskadded()
}


//    val calender=Calendar.getInstance()
//    private fun showdialogpicker() {
//        context.let {
//            var dialog=DatePickerDialog(it!!)
//            dialog.setOnDateSetListener{
//                DatePicker,day,month,year->
//                viewbinding.date.setText(
//                    "$day-$month-$year"
//                )
//                calender.set(year,month,day)
//                calender.set(Calendar.HOUR_OF_DAY,0)
//                calender.set(Calendar.MINUTE,0)
//                calender.set(Calendar.SECOND,0)
//                calender.set(Calendar.MILLISECOND,0)
//            }
//            dialog.show()
//        }
//
//
//    }


//    private fun createtask() {
//        if (!valid()){
//            return
//        }
//        var task=task(
//            title = viewbinding.titleAddtask.toString(),
//          describtion = viewbinding.descAddtask.toString(),
//            datetime = calender.timeInMillis
//        )
//        mydatabase.getinstance(requireContext()).tasks_dao().insert_task(task)
//        ontaskadded?.ontaskaddedfun()
//            dismiss()
//
//
//    }
//    var ontaskadded:ontaskaddedlistner?=null
//    fun interface ontaskaddedlistner{
//        fun ontaskaddedfun()
//    }

//    private fun valid(): Boolean {
//        var isvalid=true
//        if (viewbinding.titleAddtask.text.isNullOrBlank()){
//            viewbinding.titleContainer.error="enter the title"
//             isvalid=false
//        }else{
//           viewbinding.titleContainer.error=null
//        }
//        if (viewbinding.descAddtask.text.isNullOrBlank()){
//            viewbinding.descContainer.error="enter the describtion"
//            isvalid=false
//        }else{
//            viewbinding.descContainer.error=null
//        }
//        if (viewbinding.date.text.isNullOrBlank()){
//            viewbinding.dateContainer.error="enter the date"
//            isvalid=false
//        }else{
//            viewbinding.dateContainer.error=null
//        }
//return isvalid
//    }


}