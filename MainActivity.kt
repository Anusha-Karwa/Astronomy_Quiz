package com.example.astronomyquizapp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.example.astronomyquizapp.QnA
import android.os.Bundle
import com.example.astronomyquizapp.R
import android.content.DialogInterface
import android.graphics.Color
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var totalQuestionTextView: TextView? = null
    var questionTextView: TextView? = null
    var aA: Button? = null
    var aB: Button? = null
    var aC: Button? = null
    var aD: Button? = null
    var selected: Button? = null
    var score = 0
    var totalQue = QnA.que.size
    var currentQuestionIndex = 0
    var ansselected: String? = null


    override fun onCreate(savedInstantState: Bundle?) {
        super.onCreate(savedInstantState)
        setContentView(R.layout.activity_main)
        totalQuestionTextView = findViewById(R.id.total_questions)
        questionTextView = findViewById(R.id.question)
        aA = findViewById(R.id.answer_A)
        aB = findViewById(R.id.answer_B)
        aC = findViewById(R.id.answer_C)
        aD = findViewById(R.id.answer_D)
        selected = findViewById(R.id.selected_btn)
        aA!!.setOnClickListener(this)
        aB!!.setOnClickListener(this)
        aC!!.setOnClickListener(this)
        aD!!.setOnClickListener(this)
        selected!!.setOnClickListener(this)
        totalQuestionTextView!!.setText("Total questions are")
        loadNewQuestion()
    }

    override fun onClick(view: View) {
        aA!!.setBackgroundColor(Color.WHITE)
        aB!!.setBackgroundColor(Color.WHITE)
        aC!!.setBackgroundColor(Color.WHITE)
        aD!!.setBackgroundColor(Color.WHITE)
        val clickedButton = view as Button
        //selected = clickedButton.text.toString()
        if (clickedButton.id == R.id.selected_btn) {
            if (ansselected == QnA.correctAns[currentQuestionIndex]) {
                score++
            }
            currentQuestionIndex++
            loadNewQuestion()

        }
        else {
            ansselected = clickedButton.text.toString()
            clickedButton.setBackgroundColor(Color.GRAY)
        }
    }

    fun loadNewQuestion() {
        if (currentQuestionIndex == totalQue) {
            finishQuiz()
            return
        }
        questionTextView!!.text = QnA.que[currentQuestionIndex]
        aA!!.text = QnA.options[currentQuestionIndex][0]
        aB!!.text = QnA.options[currentQuestionIndex][1]
        aC!!.text = QnA.options[currentQuestionIndex][2]
        aD!!.text = QnA.options[currentQuestionIndex][3]
    }

    fun finishQuiz() {
        var passStatus = " "
        passStatus = if (score > totalQue * 0.60)
        {
            "Pass"
        }
        else
        {
            "Fail"
        }
        AlertDialog.Builder(this)
            .setTitle(passStatus)
            .setMessage("Score is" + score + "out of" + totalQue)
            .setPositiveButton("Restart") { dialogInterface: DialogInterface?, i: Int -> restartQuiz() }
            .setCancelable(false)
            .show()
    }

    fun restartQuiz() {
        score = 0
        currentQuestionIndex = 0
        loadNewQuestion()
    }
}