package com.example.todopractice1

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapppractice.database.task
import com.example.todopractice1.databinding.ListItemBinding




class task_adapter(var tasks: MutableList<task>?):RecyclerView.Adapter<task_adapter.viewholder>(){




class viewholder(var viewbinding:ListItemBinding):RecyclerView.ViewHolder(viewbinding.root) {
    fun bindtask(task: task) {
        viewbinding.title.text=task.title
        viewbinding.describtion.text=task.describtion
    }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val viewbinding=ListItemBinding.inflate(LayoutInflater.from(parent.context)
            ,parent,false)
        return viewholder(viewbinding)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.bindtask(tasks!!.get(position))

            if (tasks!![position].isdone == true) {
                holder.viewbinding.doneBtn.setBackgroundColor(Color.GREEN)
            }

        holder.viewbinding.leftView.setOnClickListener{

            if (onitemDeleted!=null){
                holder.viewbinding.swipeLayout.close(true)
                onitemDeleted!!.onitemclick(tasks!![position],position)
            }
        }
        if (onitemclick!=null){
            holder.viewbinding.dragItem.setOnClickListener{
                onitemclick!!.onitemclick(tasks!![position],position)

            }
        }
    }


    override fun getItemCount(): Int =tasks?.size?:0
    fun bindtask(tasks:MutableList<task>){
        this.tasks=tasks
        notifyDataSetChanged()

    }

    fun deleteview(task: task, position: Int) {
        tasks?.remove(task)
        notifyItemRemoved(position)


    }

    var onitemclick:onitemclicklisner?=null
    fun interface onitemclicklisner{
        fun onitemclick(task: task,position: Int)
    }

    var onitemDeleted:onitemclicklistner?=null
   fun interface onitemclicklistner{
        fun onitemclick(task: task,position: Int)
    }

}

