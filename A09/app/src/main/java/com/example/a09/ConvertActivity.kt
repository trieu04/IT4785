package com.example.a09

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*

class ConvertActivity : Activity() {
    private lateinit var spinnerFromCurrency: Spinner
    private lateinit var spinnerToCurrency: Spinner
    private lateinit var etFromAmount: EditText
    private lateinit var etToAmount: EditText
    
    private var isUpdating = false
    
    // Exchange rates (all relative to USD)
    private val exchangeRates = mapOf(
        "USD - Đô la Mỹ" to 1.0,
        "EUR - Euro" to 0.85,
        "GBP - Bảng Anh" to 0.73,
        "JPY - Yên Nhật" to 110.0,
        "VND - Việt Nam Đồng" to 23000.0,
        "CNY - Nhân dân tệ" to 6.45,
        "KRW - Won Hàn Quốc" to 1180.0,
        "THB - Baht Thái Lan" to 33.0,
        "SGD - Đô la Singapore" to 1.35,
        "AUD - Đô la Úc" to 1.35
    )
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convert)
        
        spinnerFromCurrency = findViewById(R.id.spinnerFromCurrency)
        spinnerToCurrency = findViewById(R.id.spinnerToCurrency)
        etFromAmount = findViewById(R.id.etFromAmount)
        etToAmount = findViewById(R.id.etToAmount)
        
        setupSpinners()
        setupTextWatchers()
    }
    
    private fun setupSpinners() {
        val currencies = exchangeRates.keys.toList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        
        spinnerFromCurrency.adapter = adapter
        spinnerToCurrency.adapter = adapter
        
        spinnerFromCurrency.setSelection(0) // USD
        spinnerToCurrency.setSelection(4) // VND
        
        spinnerFromCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                convertCurrency(true)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        
        spinnerToCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                convertCurrency(true)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    
    private fun setupTextWatchers() {
        etFromAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (!isUpdating) {
                    convertCurrency(true)
                }
            }
        })
        
        etToAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (!isUpdating) {
                    convertCurrency(false)
                }
            }
        })
    }
    
    private fun convertCurrency(fromToTo: Boolean) {
        isUpdating = true
        
        try {
            if (fromToTo) {
                // Convert from "From" to "To"
                val amountText = etFromAmount.text.toString()
                if (amountText.isNotEmpty()) {
                    val amount = amountText.toDouble()
                    val fromCurrency = spinnerFromCurrency.selectedItem.toString()
                    val toCurrency = spinnerToCurrency.selectedItem.toString()
                    
                    val fromRate = exchangeRates[fromCurrency] ?: 1.0
                    val toRate = exchangeRates[toCurrency] ?: 1.0
                    
                    val result = amount / fromRate * toRate
                    etToAmount.setText(String.format("%.2f", result))
                } else {
                    etToAmount.setText("")
                }
            } else {
                // Convert from "To" to "From"
                val amountText = etToAmount.text.toString()
                if (amountText.isNotEmpty()) {
                    val amount = amountText.toDouble()
                    val fromCurrency = spinnerFromCurrency.selectedItem.toString()
                    val toCurrency = spinnerToCurrency.selectedItem.toString()
                    
                    val fromRate = exchangeRates[fromCurrency] ?: 1.0
                    val toRate = exchangeRates[toCurrency] ?: 1.0
                    
                    val result = amount / toRate * fromRate
                    etFromAmount.setText(String.format("%.2f", result))
                } else {
                    etFromAmount.setText("")
                }
            }
        } catch (e: NumberFormatException) {
            // Handle invalid input
        }
        
        isUpdating = false
    }
}
