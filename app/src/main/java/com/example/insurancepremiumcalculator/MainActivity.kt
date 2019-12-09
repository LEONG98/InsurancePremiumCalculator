package com.example.insurancepremiumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var premium:PremiumModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        premium = ViewModelProviders.of(this).get(PremiumModel::class.java)

        display()

        btnCalculate.setOnClickListener(){
            calculatePremium()
        }

        btnReset.setOnClickListener(){
            reset()
        }
    }

    private fun calculatePremium(){
        premium.premiumAmount = getPremium()
        display()
    }

    fun display(){
        if(premium.premiumAmount != 0.0)
            txtTotal.text = "RM " + premium.premiumAmount + "0"
    }

    fun getPremium():Double{
        return when(spnAge.selectedItemPosition){
            0 -> 60.00
            1 -> 70.00 + (if(rbMale.isChecked) 50.00 else 0.0) + (if(cbSmoker.isChecked) 100.00 else 0.0)
            2 -> 90.00 + (if(rbMale.isChecked) 100.00 else 0.0) + (if(cbSmoker.isChecked) 150.00 else 0.0)
            3 -> 120.00 + (if(rbMale.isChecked) 150.00 else 0.0) + (if(cbSmoker.isChecked) 200.00 else 0.0)
            4 -> 150.00 + (if(rbMale.isChecked) 200.00 else 0.0) + (if(cbSmoker.isChecked) 250.00 else 0.0)
            else -> 150.00 + (if(rbMale.isChecked) 200.00 else 0.0) + (if(cbSmoker.isChecked) 300.00 else 0.0)
        }
    }

    private fun reset(){
        spnAge.setSelection(0)
        rbMale.setChecked(false)
        rbFemale.setChecked(false)
        cbSmoker.setChecked(false)
        txtTotal.setText("")
        premium.premiumAmount = 0.0
    }
}
