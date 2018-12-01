package com.example.alunos.clickclick

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class PontuacaoActivity : AppCompatActivity() {

    internal lateinit var jogarNovamente: Button
    internal lateinit var gameScoreTextView2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pontuacao)

        jogarNovamente = findViewById<Button>(R.id.jogar_novamente)
        gameScoreTextView2 = findViewById<TextView>(R.id.game_score_text_view2)

    }





    fun jogarNovamente(view: View){
       // MainActivity.resetGame()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
