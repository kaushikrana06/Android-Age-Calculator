package com.example.ageinmunites

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button2.setOnClickListener {view->
            date(view)
            }


    }
    fun date(view:View){
        val cal=Calendar.getInstance()//calendar is the class
        //These are only the variable we are passing
        val i3=cal.get(Calendar.YEAR)
        val i2=cal.get(Calendar.MONTH)
        val i=cal.get(Calendar.DAY_OF_MONTH)
        //The below  i, i2, i3 -> actually contains the value
       val d = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, i, i2, i3 ->
                Toast.makeText(this,"Date selected !",Toast.LENGTH_LONG).show()

                val date ="$i3/${i2+1}/$i"// format of viewing the date
                textView.setText(date) //the empty space below select date button

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)// simple date format class is used

                val theDate = sdf.parse(date)//parse is the function in SDF class

                val selcyr = theDate!!.time/31104000000 // 1000 for sec 60000 for minutes 3600000 hrs 86400000 days 31104000000 YRS
                val curr = sdf.parse(sdf.format(System.currentTimeMillis()))//bringing into right format
                val curryr = curr!!.time/31104000000
                val diffyr = curryr - selcyr
                val seldy = theDate!!.time/86400000
                val curdy = curr!!.time/86400000
                val diffdy = curdy - seldy
                val selhr = theDate!!.time/3600000
                val curhr = curr!!.time/3600000
                val diffhr = curhr - selhr
                val selmin = theDate!!.time/60000
                val curmin = curr!!.time/60000
                val diffmin = curmin - selmin
                textView5.setText(diffyr.toString())// for Year string so that it will have proper font size
                textView10.setText(diffdy.toString())//For days
                textView11.setText(diffhr.toString())//Hours
                textView12.setText(diffmin.toString())//minutes

            },i,i2,i3)
            d.datePicker.setMaxDate(Date().time - 86400000)// it will limit the selection of date after the current date
            d.show()

    }
}