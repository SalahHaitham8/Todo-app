    package com.example.todopractice1

    import android.app.AlertDialog
    import android.app.ProgressDialog
    import android.content.DialogInterface
    import androidx.appcompat.app.AppCompatActivity

    class DialogActivity: AppCompatActivity() {
        var progressdialog:ProgressDialog?=null

        fun showdialog()
        {
            progressdialog= ProgressDialog(this)
            progressdialog?.setMessage("loading")
            progressdialog?.show()
        }
        fun hideDialog()
        {
            progressdialog?.dismiss()

        }

        var alertdialog:AlertDialog?=null

        fun showmessage(message:String,
                        posActionTitle:String?=null,
                        posAction:DialogInterface.OnClickListener?=null
        ,negActionTitle:String?=null
        ,negAction:DialogInterface.OnClickListener?=null
        ,cancelable:Boolean=true
                        )
        {
        val messagedialogBuilder=AlertDialog.Builder(this)
            messagedialogBuilder.setMessage(message)
            if (negActionTitle!=null)
            {
                messagedialogBuilder.setPositiveButton(negActionTitle,negAction?:DialogInterface.OnClickListener{
                        dialog, which ->
                    dialog.dismiss()
                })
            }
            if (posActionTitle!=null)
            {
                messagedialogBuilder.setPositiveButton(posActionTitle,posAction?:DialogInterface.OnClickListener{
                    dialog, which ->
                    dialog.dismiss()
                })
            }

            messagedialogBuilder.setCancelable(cancelable)
            alertdialog=messagedialogBuilder.show()

        }

    }