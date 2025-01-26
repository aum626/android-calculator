package com.chanondev626.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Stack

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
    @SuppressLint("SetTextI18n")
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
        val btnParenthesesClose = findViewById<Button>(R.id.btnP_close)
        val btnParenthesesOpen = findViewById<Button>(R.id.btnP_open)
        val btnBack = findViewById<Button>(R.id.btnBackspace)

        // allButton
        btnParenthesesOpen.setOnClickListener{
            textView.append("(")
        }
        btnParenthesesClose.setOnClickListener{
            textView.append(")")
        }
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
        btnDot.setOnClickListener{
            textView.append(".")
        }

        btnEqual.setOnClickListener{
            try {
                val expression = textView.text.toString()
                val postfix = infixToPostfix(expression)
                val result = evaluatePostfix(postfix)
                textView.text = result.toString()
            } catch (e: Exception) {
                textView.text = "Error"
            }
        }
    }

    private fun infixToPostfix(expression: String): String {
        val precedence = mapOf('+' to 1, '-' to 1, '*' to 2, '/' to 2)
        val stack = Stack<Char>()
        val postfix = StringBuilder()
        var i = 0

        while (i < expression.length) {
            val char = expression[i]
            when {
                char.isDigit() || char == '.' -> {
                    postfix.append(char)
                    while (i + 1 < expression.length && (expression[i + 1].isDigit() || expression[i + 1] == '.')) {
                        postfix.append(expression[++i])
                    }
                    postfix.append(' ')
                }
                char == '(' -> stack.push(char)
                char == ')' -> {
                    while (stack.peek() != '(') {
                        postfix.append(stack.pop()).append(' ')
                    }
                    stack.pop()
                }
                else -> {
                    while (stack.isNotEmpty() && precedence.getOrDefault(char, 0) <= precedence.getOrDefault(stack.peek(), 0)) {
                        postfix.append(stack.pop()).append(' ')
                    }
                    stack.push(char)
                }
            }
            i++
        }

        while (stack.isNotEmpty()) {
            postfix.append(stack.pop()).append(' ')
        }
        return postfix.toString().trim()
    }

    private fun evaluatePostfix(postfix: String): Double {
        val stack = Stack<Double>()
        val tokens = postfix.split(' ')
        try {
            tokens.forEach {
                when {
                    it.toDoubleOrNull() != null -> stack.push(it.toDouble())
                    else -> {
                        val b = stack.pop()
                        val a = stack.pop()
                        stack.push(when (it) {
                            "+" -> a + b
                            "-" -> a - b
                            "*" -> a * b
                            "/" -> a / b
                            else -> throw IllegalArgumentException("Invalid operator")
                        })
                    }
                }
            }
        } catch (e: Exception) {
            return 0.0
        }
        return stack.pop()
    }






}