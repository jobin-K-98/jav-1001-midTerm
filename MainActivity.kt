package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
  // Declaring private variables for UI components
    private lateinit var inputValueLayout: TextInputLayout
    private lateinit var output: TextView
    private lateinit var convertFrom: Spinner
    private lateinit var outputUnit: TextView

  
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing the UI components using their IDs
        inputValueLayout = findViewById(R.id.enteredValue)
        convertFrom = findViewById(R.id.convertFromUnit)
        output = findViewById(R.id.answer)
        outputUnit = findViewById(R.id.answerUnit)

        
        // Set the onItemSelectedListener for the Spinner to convert the units on item selection
        convertFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calculateResult(convertFrom.selectedItem.toString(), inputValueLayout.editText?.text.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        
        
        // Set the TextWatcher for the EditText to convert the units on text change
        inputValueLayout.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateResult(convertFrom.selectedItem.toString(), s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
    
    // Function to convert the value to the specified unit
    // This function is created in such a way that each unit will be converted to a specified unit that is hard coded.
    private fun calculateResult(fromUnit: String, inputValue: String) {
      
        val input = inputValue.toFloatOrNull()
        
        // Declare variables to store the result and result unit
        var result: Double? = null
        var resultUnit: String? = null
        
      // The core of the function
        if (input != null && fromUnit != null) {
            when (fromUnit) {
                "Kilometer" -> {
                    result = input / 1.609
                    resultUnit = "Miles"
                }

                "Miles" -> {
                    result =  input * 1.609
                    resultUnit = "Kilometer"
                }
                "Centimeter" -> {
                    result = input / 2.54
                    resultUnit = "Inches"
                }
                "Inches" -> {
                    result = input * 2.54
                    resultUnit = "Centimeter"
                }
                "Celsius" -> {
                    result = (input * 1.8) + 32
                    resultUnit = "Fahrenheit"
                }
                "Fahrenheit" -> {
                    result = (input - 32) * 0.55
                    resultUnit = "Celsius"
                }
                "Kelvin" -> {
                    result = input - 273.15
                    resultUnit = "Celsius"
                }
                else -> print("Invalid input! Try again.")
            }
        }

        output.text = result?.toString() ?: ""
        outputUnit.text = resultUnit?.toString() ?: ""
    }
}
