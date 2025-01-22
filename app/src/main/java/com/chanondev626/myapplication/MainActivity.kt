package com.chanondev626.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        allBtn()

    }
    private fun allBtn(){
        // allButton
        val textView = findViewById<TextView>(R.id.textView)
        val btn0 = findViewById<Button>(R.id.btn0)
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)
        val btnPlus = findViewById<Button>(R.id.btnPlus)
        val btnMinus = findViewById<Button>(R.id.btnMinus)
        val btnMultiply = findViewById<Button>(R.id.btnMultiply)
        val btnDivide = findViewById<Button>(R.id.btnDivide)
        val btnEqual = findViewById<Button>(R.id.btnEquals)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val btnDot = findViewById<Button>(R.id.btnDecimal)
        val btnPercent = findViewById<Button>(R.id.btnPercent)
        val btnNegative = findViewById<Button>(R.id.btnNegative)
        val btnBack = findViewById<Button>(R.id.btnBackspace)

        // allButton
        btn0.setOnClickListener{
            textView.append("0")
        }
        btn1.setOnClickListener{
            textView.append("1")
        }
        btn2.setOnClickListener{
            textView.append("2")
        }
        btn3.setOnClickListener{
            textView.append("3")
        }
        btn4.setOnClickListener{
            textView.append("4")
        }
        btn5.setOnClickListener{
            textView.append("5")
        }
        btn6.setOnClickListener{
            textView.append("6")
        }
        btn7.setOnClickListener{
            textView.append("7")
        }
        btn8.setOnClickListener{
            textView.append("8")
        }
        btn9.setOnClickListener{
            textView.append("9")
        }
        btnPlus.setOnClickListener{
            textView.append("+")
        }
        btnMinus.setOnClickListener{
            textView.append("-")
        }
        btnMultiply.setOnClickListener{
            textView.append("*")
        }
        btnDivide.setOnClickListener{
            textView.append("/")
        }
        btnClear.setOnClickListener{
            textView.text = ""
        }
        btnBack.setOnClickListener{
            val str = textView.text.toString()
            if (str.isNotEmpty()){
                textView.text = str.substring(0, str.length - 1)
            }
        }
    }

}