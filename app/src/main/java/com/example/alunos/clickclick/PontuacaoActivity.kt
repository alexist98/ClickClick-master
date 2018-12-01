package com.example.alunos.clickclick

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class PontuacaoActivity : AppCompatActivity() {

    internal lateinit var jogarNovamente: Button
    internal lateinit var gameScoreTextView2: TextView
    var score = MainActivity.score1.toString()
    /*var top1 = 0
    var top2 = 0
    var top3 = 0*/
    companion object {
        var top1 = 0
        var top2 = 0
        var top3 = 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val numbers: MutableList<Int> = mutableListOf(top1, top2, top3)
        val readOnlyView: List<Int> = numbers
        setContentView(R.layout.activity_pontuacao)

        jogarNovamente = findViewById<Button>(R.id.jogar_novamente)
        gameScoreTextView2 = findViewById<TextView>(R.id.game_score_text_view2)
        gameScoreTextView2.text = getString(R.string.sua_pontuacao, score)

        numbers.add(score.toInt())
        val ordenado = numbers.sortedWith((reverseOrder()))
        top1=ordenado.component1()
        top2=ordenado.component2()
        top3=ordenado.component3()
    }





    fun jogarNovamente(view: View){
       // MainActivity.resetGame()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
