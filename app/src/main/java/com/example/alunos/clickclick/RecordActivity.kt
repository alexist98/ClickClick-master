package com.example.alunos.clickclick

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class RecordActivity : AppCompatActivity() {

    internal lateinit var top1TextView: TextView
    internal lateinit var top2TextView: TextView
    internal lateinit var top3TextView: TextView

    var top1 = PontuacaoActivity.top1.toString()
    var top2 = PontuacaoActivity.top2.toString()
    var top3 = PontuacaoActivity.top3.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        top1TextView = findViewById<TextView>(R.id.top1_text_view)
        top1TextView.text = getString(R.string.sua_pontuacao, top1.toString())

        top2TextView = findViewById<TextView>(R.id.top2_text_view)
        top2TextView.text = getString(R.string.sua_pontuacao, top2.toString())

        top3TextView = findViewById<TextView>(R.id.top3_text_view)
        top3TextView.text = getString(R.string.sua_pontuacao, top3.toString())
    }
}
