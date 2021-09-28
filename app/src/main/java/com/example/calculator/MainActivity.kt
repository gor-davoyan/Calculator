package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    var button1: Button? = null
    var button2: Button? = null
    var button3: Button? = null
    var button4: Button? = null
    var button5: Button? = null
    var button6: Button? = null
    var button7: Button? = null
    var button8: Button? = null
    var button9: Button? = null
    var button0: Button? = null
    var editText: EditText? = null
    var buttonDot: Button? = null
    var buttonMultiplication: Button? = null
    var buttonDivision: Button? = null
    var buttonSubtraction: Button? = null
    var buttonAddition: Button? = null
    var buttonEqual: Button? = null
    var buttonClear: Button? = null

    var isNewOp = true
    var oldNumber = ""
    var op = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)
        button0 = findViewById(R.id.button0)
        editText = findViewById(R.id.editTextOperation)
        buttonDot = findViewById(R.id.buttonDot)
        buttonMultiplication = findViewById(R.id.buttonMultiplication)
        buttonDivision = findViewById(R.id.buttonDivision)
        buttonSubtraction = findViewById(R.id.buttonSubtraction)
        buttonAddition = findViewById(R.id.buttonAddition)
        buttonEqual = findViewById(R.id.buttonEqual)
        buttonClear = findViewById(R.id.buttonClear)

        mutableListOf(
            buttonDot,
            button1,
            button2,
            button3,
            button4,
            button5,
            button6,
            button7,
            button8,
            button9,
            button0
        ).run { this.forEach { button -> button?.setOnClickListener { numberEvent(it) } } }

        mutableListOf(buttonMultiplication, buttonDivision, buttonSubtraction, buttonAddition)
            .run { this.forEach { button -> button?.setOnClickListener { operatorEvent(it) } } }

        buttonEqual?.setOnClickListener { equalEvent() }

        buttonClear?.setOnClickListener {
            editText?.setText("")
            op = ""
        }
    }

    private fun equalEvent() {
        val curNumber = editText?.text.toString()
        if (curNumber.isEmpty()) {
            editText?.setText("Invalid operation")
            op = ""
        } else {
            var result = "0.0"
            when (op) {
                "*" -> result = (oldNumber.toDouble() * curNumber.toDouble()).toString()
                "/" -> result = if (curNumber == "0") "Can't divide by 0" else
                    (oldNumber.toDouble() / curNumber.toDouble()).toString()
                "-" -> result = (oldNumber.toDouble() - curNumber.toDouble()).toString()
                "+" -> result = (oldNumber.toDouble() + curNumber.toDouble()).toString()
            }
            editText?.setText(result)
            op = ""
        }
    }

    private fun operatorEvent(view: View) {
        isNewOp = true
        oldNumber = editText?.text.toString()
        op = view.tag.toString()
    }

    private fun numberEvent(view: View) {
        if (isNewOp) editText?.setText("")
        isNewOp = false
        var text = editText?.text.toString()
        text += view.tag.toString()
        editText?.setText(text)
    }
}
