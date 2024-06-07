package com.example.todopractice1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.todopractice1.databinding.ActivityHomeBinding
import com.example.todopractice1.fragments.List_fragment
import com.example.todopractice1.fragments.addtask_fragment
import com.google.android.material.snackbar.Snackbar

class Home_activity : AppCompatActivity() {
    lateinit var viewbinding: ActivityHomeBinding
    var listFragmentRef:List_fragment?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewbinding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewbinding.root)

     viewbinding.botomNav.setOnItemSelectedListener {
         when (it.itemId) {
             R.id.list_icon -> {
                 listFragmentRef=List_fragment()
                 showfragment(listFragmentRef!!)

             }
             R.id.setting_icon->{showfragment(setting_fragment())}

         }
         return@setOnItemSelectedListener true
     }

        viewbinding.botomNav.selectedItemId=R.id.list_icon

        viewbinding.addtask.setOnClickListener{
            showaddtaskfragment()
        }


    }

    private fun showaddtaskfragment() {
        val addtask=addtask_fragment()
        addtask.Ontaskaddedlistner= addtask_fragment.ontaskaddedlistner {
            Snackbar
                .make(viewbinding.root,"task added succesfully",Snackbar.LENGTH_LONG)
                .show()
          listFragmentRef?.loadtasks()
        }
        addtask.show(supportFragmentManager,"")
    }

    private fun showfragment(fragment:Fragment){
    supportFragmentManager
        .beginTransaction()
        .replace(R.id.container,fragment)
        .setCustomAnimations(R.anim.fade_in,R.anim.fade_out)
        .commit()

}

}
/*












  viewbinding.botomNav.setOnItemSelectedListener{
      when(it.itemId){
          R.id.list_icon->{
              supportFragmentManager.beginTransaction().replace(R.id.container, List_fragment()).commit()
          }
          R.id.setting_icon->{
              supportFragmentManager.beginTransaction().replace(R.id.container, setting_fragment()).commit()
          }
      }
      return@setOnItemSelectedListener true
  }
        viewbinding.botomNav.selectedItemId=R.id.list_icon
        viewbinding.addtask.setOnClickListener{
            showaddtaskfragment()
        }

          private fun showaddtaskfragment() {

        val addtask=addtask_fragment()
        addtask.ontaskadded=addtask_fragment.ontaskaddedlistner{
          val toast=Toast.makeText(this,"task added succesfully",Toast.LENGTH_LONG)
            toast.show()
        }
        addtask.show(supportFragmentManager,"")

    }
 */