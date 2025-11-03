package com.example.hoc

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CalcActivity : Activity() {
    private lateinit var resultTextView: TextView
    
    private var currentValue = "0"
    private var previousValue = ""
    private var operator = ""
    private var isNewOperation = true
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calc)
        
        resultTextView = findViewById(R.id.resultTextView)
        
        // Number buttons
        val numberButtons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )
        
        numberButtons.forEach { id ->
            findViewById<Button>(id).setOnClickListener { view ->
                onNumberClick((view as Button).text.toString())
            }
        }
        
        // Operator buttons
        findViewById<Button>(R.id.btnPlus).setOnClickListener { onOperatorClick("+") }
        findViewById<Button>(R.id.btnMinus).setOnClickListener { onOperatorClick("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { onOperatorClick("x") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { onOperatorClick("/") }
        
        // Special buttons
        findViewById<Button>(R.id.btnEquals).setOnClickListener { onEqualsClick() }
        findViewById<Button>(R.id.btnDot).setOnClickListener { onDotClick() }
        findViewById<Button>(R.id.btnPlusMinus).setOnClickListener { onPlusMinusClick() }
        findViewById<Button>(R.id.btnCE).setOnClickListener { onCEClick() }
        findViewById<Button>(R.id.btnC).setOnClickListener { onCClick() }
        findViewById<Button>(R.id.btnBS).setOnClickListener { onBSClick() }
    }
    
    private fun onNumberClick(number: String) {
        if (isNewOperation || currentValue == "0") {
            currentValue = number
            isNewOperation = false
        } else {
            currentValue += number
        }
        updateDisplay()
    }
    
    private fun onOperatorClick(op: String) {
        if (previousValue.isNotEmpty() && !isNewOperation) {
            calculate()
        }
        operator = op
        previousValue = currentValue
        isNewOperation = true
    }
    
    private fun onEqualsClick() {
        if (previousValue.isNotEmpty() && operator.isNotEmpty()) {
            calculate()
            operator = ""
            previousValue = ""
        }
    }
    
    private fun calculate() {
        try {
            val prev = previousValue.toDoubleOrNull() ?: return
            val curr = currentValue.toDoubleOrNull() ?: return
            
            val result = when (operator) {
                "+" -> prev + curr
                "-" -> prev - curr
                "x" -> prev * curr
                "/" -> {
                    if (curr != 0.0) prev / curr
                    else {
                        currentValue = "Error"
                        updateDisplay()
                        return
                    }
                }
                else -> return
            }
            
            // Convert to integer if it's a whole number
            currentValue = if (result % 1 == 0.0) {
                result.toInt().toString()
            } else {
                result.toString()
            }
            
            isNewOperation = true
            updateDisplay()
        } catch (e: Exception) {
            currentValue = "Error"
            updateDisplay()
        }
    }
    
    private fun onDotClick() {
        if (!currentValue.contains(".")) {
            if (isNewOperation) {
                currentValue = "0."
                isNewOperation = false
            } else {
                currentValue += "."
            }
            updateDisplay()
        }
    }
    
    private fun onPlusMinusClick() {
        if (currentValue != "0" && currentValue != "Error") {
            currentValue = if (currentValue.startsWith("-")) {
                currentValue.substring(1)
            } else {
                "-$currentValue"
            }
            updateDisplay()
        }
    }
    
    private fun onCEClick() {
        currentValue = "0"
        isNewOperation = true
        updateDisplay()
    }
    
    private fun onCClick() {
        currentValue = "0"
        previousValue = ""
        operator = ""
        isNewOperation = true
        updateDisplay()
    }
    
    private fun onBSClick() {
        if (currentValue.length > 1 && currentValue != "Error") {
            currentValue = currentValue.substring(0, currentValue.length - 1)
        } else {
            currentValue = "0"
        }
        updateDisplay()
    }
    
    private fun updateDisplay() {
        resultTextView.text = currentValue
    }
}
