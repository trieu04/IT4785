package com.example.a09

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import kotlin.math.sqrt

class IntegerActivity : Activity() {
    private lateinit var etNumber: EditText
    private lateinit var rbEven: RadioButton
    private lateinit var rbPrime: RadioButton
    private lateinit var rbPerfect: RadioButton
    private lateinit var rbOdd: RadioButton
    private lateinit var rbSquare: RadioButton
    private lateinit var rbFibonacci: RadioButton
    private lateinit var lvNumbers: ListView
    private lateinit var tvNoResult: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_integer)
        
        etNumber = findViewById(R.id.etNumber)
        rbEven = findViewById(R.id.rbEven)
        rbPrime = findViewById(R.id.rbPrime)
        rbPerfect = findViewById(R.id.rbPerfect)
        rbOdd = findViewById(R.id.rbOdd)
        rbSquare = findViewById(R.id.rbSquare)
        rbFibonacci = findViewById(R.id.rbFibonacci)
        lvNumbers = findViewById(R.id.lvNumbers)
        tvNoResult = findViewById(R.id.tvNoResult)
        
        setupListeners()
        updateList()
    }
    
    private fun setupListeners() {
        etNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                updateList()
            }
        })
        
        val radioButtons = listOf(rbEven, rbPrime, rbPerfect, rbOdd, rbSquare, rbFibonacci)
        
        radioButtons.forEach { radioButton ->
            radioButton.setOnClickListener {
                radioButtons.forEach { it.isChecked = false }
                radioButton.isChecked = true
                updateList()
            }
        }
    }
    
    private fun updateList() {
        val numberText = etNumber.text.toString()
        if (numberText.isEmpty()) {
            showNoResult()
            return
        }
        
        try {
            val n = numberText.toInt()
            if (n <= 0) {
                showNoResult()
                return
            }
            
            val numbers = when {
                rbEven.isChecked -> getOddNumbers(n)
                rbPrime.isChecked -> getPrimeNumbers(n)
                rbPerfect.isChecked -> getPerfectNumbers(n)
                rbOdd.isChecked -> getEvenNumbers(n)
                rbSquare.isChecked -> getSquareNumbers(n)
                rbFibonacci.isChecked -> getFibonacciNumbers(n)
                else -> getOddNumbers(n)
            }
            
            if (numbers.isEmpty()) {
                showNoResult()
            } else {
                showList(numbers)
            }
        } catch (e: NumberFormatException) {
            showNoResult()
        }
    }
    
    private fun showList(numbers: List<Int>) {
        tvNoResult.visibility = View.GONE
        lvNumbers.visibility = View.VISIBLE
        
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            numbers
        )
        lvNumbers.adapter = adapter
    }
    
    private fun showNoResult() {
        tvNoResult.visibility = View.VISIBLE
        lvNumbers.visibility = View.GONE
    }
    
    private fun getOddNumbers(n: Int): List<Int> {
        return (1 until n).filter { it % 2 == 1 }
    }
    
    private fun getEvenNumbers(n: Int): List<Int> {
        return (2 until n).filter { it % 2 == 0 }
    }
    
    private fun getPrimeNumbers(n: Int): List<Int> {
        return (2 until n).filter { isPrime(it) }
    }
    
    private fun isPrime(num: Int): Boolean {
        if (num < 2) return false
        if (num == 2) return true
        if (num % 2 == 0) return false
        for (i in 3..sqrt(num.toDouble()).toInt() step 2) {
            if (num % i == 0) return false
        }
        return true
    }
    
    private fun getPerfectNumbers(n: Int): List<Int> {
        return (1 until n).filter { isPerfect(it) }
    }
    
    private fun isPerfect(num: Int): Boolean {
        if (num < 1) return false
        var sum = 0
        for (i in 1 until num) {
            if (num % i == 0) sum += i
        }
        return sum == num
    }
    
    private fun getSquareNumbers(n: Int): List<Int> {
        val result = mutableListOf<Int>()
        var i = 1
        while (i * i < n) {
            result.add(i * i)
            i++
        }
        return result
    }
    
    private fun getFibonacciNumbers(n: Int): List<Int> {
        val result = mutableListOf<Int>()
        var a = 0
        var b = 1
        
        while (a < n) {
            if (a > 0) result.add(a)
            val temp = a + b
            a = b
            b = temp
        }
        
        return result
    }
}
