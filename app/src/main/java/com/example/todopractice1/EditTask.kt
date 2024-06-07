package com.example.todopractice1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.todoapppractice.database.task
import com.example.todopractice1.databinding.ActivityEditTaskBinding
import com.example.todopractice1.datatbase.mydatabase
import java.text.SimpleDateFormat
import java.util.*

class EditTask : AppCompatActivity() {
    lateinit var viewbinding:ActivityEditTaskBinding
    private lateinit var task:task
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbinding= ActivityEditTaskBinding.inflate(layoutInflater)
        setContentView(viewbinding.root)
        GetTask()
        showdata(task)
        viewbinding.editBtn.setOnClickListener{

            updatetask()
        }
    }

    private fun GetTask() {
        task=((intent.getSerializableExtra("task")as task)!!)
    }

    private fun showdata(task: task) {
        viewbinding.title.setText(task.title)
        viewbinding.describtion.setText(task.describtion)
        var date=ConvertToTime(task.datetime)
        viewbinding.selectedDate.text=date

    }

    private fun ConvertToTime(datetime: Long?): String {
        val date=Date(datetime!!)
        val format=SimpleDateFormat("yy/MM/dd")
        return format.format(date)
    }

    private fun vaidate(): Boolean {
        var validate=true
        var title=viewbinding.titleContainer.editText.toString()
        var describtion=viewbinding.describtionContainer.editText.toString()
        if (title.isNullOrBlank()){
            viewbinding.titleContainer.error=
            "please enter title"
            validate=false
        }
        else{
            viewbinding.titleContainer.error=null
        }
        if (describtion.isNullOrBlank()){
            viewbinding.describtionContainer.error=
                "please enter describtion"
            validate=false
        }
        else{
            viewbinding.titleContainer.error=null
        }

return validate
    }

    private fun updatetask() {
if (vaidate()==false){
    return
}
        task.title=viewbinding.title.text.toString()
        task.describtion=viewbinding.describtion.text.toString()
        mydatabase.getinstance(applicationContext).tasks_dao().Update_task(task)
        showalertdialog()
        startActivity(Intent(this,Home_activity::class.java))
        finish()


    }

    private fun showalertdialog() {
        val alertdialog=AlertDialog.Builder(this)
            .setMessage("updated successfully")
            .setPositiveButton(R.string.ok)
            {dialog,which->dialog.dismiss()}
        alertdialog.show()

    }

}