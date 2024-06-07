package com.example.todopractice1

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import androidx.fragment.app.Fragment

open class DialogFragment :Fragment() {
    var progressdialog: ProgressDialog?=null

    fun showdialog()
    {
        progressdialog= ProgressDialog(requireContext())
        progressdialog?.setMessage("loading")
        progressdialog?.show()
    }
    fun hideDialog()
    {
        progressdialog?.dismiss()

    }

    var alertdialog: AlertDialog?=null

    fun showmessage(message:String,
                    posActionTitle:String?=null,
                    posAction: DialogInterface.OnClickListener?=null,
                    negActionTitle:String?=null,
                    negAction: DialogInterface.OnClickListener?=null,
                    cancelable:Boolean=true
    )
    {
        val messagedialogBuilder= AlertDialog.Builder(requireContext())
        messagedialogBuilder.setMessage(message)

        if (posActionTitle!=null)
        {
            messagedialogBuilder.setPositiveButton(posActionTitle,posAction?: DialogInterface.OnClickListener{
                    dialog, which ->
                dialog.dismiss()
            })
        }
        if (negActionTitle!=null)
        {
            messagedialogBuilder.setNegativeButton(negActionTitle,negAction?: DialogInterface.OnClickListener{
                    dialog, which ->
                dialog.dismiss()
            })
        }

        messagedialogBuilder.setCancelable(cancelable)
        alertdialog=messagedialogBuilder.show()

    }
}