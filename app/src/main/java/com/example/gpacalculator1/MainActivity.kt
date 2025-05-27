package com.example.gpacalculator1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge

import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import kotlin.math.pow

fun convertToGrade(score: Int): Int {
    return when {
        score >= 90 -> 10
        score >= 80 -> 9
        score >= 70 -> 8
        score >= 60 -> 7
        score >= 50 -> 6
        score >= 40 -> 5
        else -> 0
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val m1=findViewById<EditText>(R.id.m1)
        val m2=findViewById<EditText>(R.id.m2)
        val m3=findViewById<EditText>(R.id.m3)
        val m4=findViewById<EditText>(R.id.m4)
        val c1=findViewById<EditText>(R.id.c1)
        val c2=findViewById<EditText>(R.id.c2)
        val c3=findViewById<EditText>(R.id.c3)
        val c4=findViewById<EditText>(R.id.c4)
        val markBox = findViewById<EditText>(R.id.markBox)
        val credBox = findViewById<EditText>(R.id.credBox)
        val outputBox = findViewById<TextView>(R.id.gpaResultText)
        val calcButton = findViewById<Button>(R.id.calculateGpaButton)

        calcButton.setOnClickListener {
            val m1Input = m1.text.toString().trim()
            val m2Input = m2.text.toString().trim()
            val m3Input = m3.text.toString().trim()
            val m4Input = m4.text.toString().trim()
            val c1Input = c1.text.toString().trim()
            val c2Input = c2.text.toString().trim()
            val c3Input = c3.text.toString().trim()
            val c4Input = c4.text.toString().trim()
            val credInput = credBox.text.toString().trim()
            val markInput = markBox.text.toString().trim()

            // Check each pair individually and clear only that pair if incomplete
            var incompletePairFound = false

            if (c1Input.isEmpty() xor m1Input.isEmpty()) {
                c1.setText("")
                m1.setText("")
                incompletePairFound = true
            }
            if (c2Input.isEmpty() xor m2Input.isEmpty()) {
                c2.setText("")
                m2.setText("")
                incompletePairFound = true
            }
            if (c3Input.isEmpty() xor m3Input.isEmpty()) {
                c3.setText("")
                m3.setText("")
                incompletePairFound = true
            }
            if (c4Input.isEmpty() xor m4Input.isEmpty()) {
                c4.setText("")
                m4.setText("")
                incompletePairFound = true
            }
            if (credInput.isEmpty() xor markInput.isEmpty()) {
                credBox.setText("")
                markBox.setText("")
                incompletePairFound = true
            }

            if (incompletePairFound) {
                outputBox.setText("")
                Toast.makeText(this@MainActivity, "Please fill both credits and marks or leave both empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val cc1 = (if (c1Input.isEmpty()) 0 else c1Input.toInt())
            val cc2 = (if (c2Input.isEmpty()) 0 else c2Input.toInt())
            val cc3 = (if (c3Input.isEmpty()) 0 else c3Input.toInt())
            val cc4 = (if (c4Input.isEmpty()) 0 else c4Input.toInt())
            val wt = (if (credInput.isEmpty()) 0 else credInput.toInt())
            val ss1 = convertToGrade(if (m1Input.isEmpty()) 0 else m1Input.toInt())
            val ss2 = convertToGrade(if (m2Input.isEmpty()) 0 else m2Input.toInt())
            val ss3 = convertToGrade(if (m3Input.isEmpty()) 0 else m3Input.toInt())
            val ss4 = convertToGrade(if (m4Input.isEmpty()) 0 else m4Input.toInt())
            val ht = convertToGrade(if (markInput.isEmpty()) 0 else markInput.toInt())

            val numerator = (cc1 * ss1) + (cc2 * ss2) + (cc3 * ss3) + (cc4 * ss4) + (wt * ht)
            val denominator = cc1 + cc2 + cc3 + cc4 + wt

            val cgpa = if (denominator != 0) numerator.toDouble() / denominator else 0.0
            val way = DecimalFormat("0.00")
            outputBox.setText(way.format(cgpa))

        }

    }
}
/*
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
*/
