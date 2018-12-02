package com.example.alunos.clickclick

import android.content.Intent
import android.icu.text.AlphabeticIndex
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.alunos.clickclick.R.*
import com.example.alunos.clickclick.R.id.record
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal lateinit var tapMeButton: Button
    internal lateinit var gameScoreTextView: TextView
    internal lateinit var timeLeftTextView: TextView
    var score = 0
    internal var gameStarted = false
    internal lateinit var countDownTimer: CountDownTimer
    internal val initialCountDown: Long = 10000
    internal val countDownInterval: Long = 1000
    internal  val  TAG = MainActivity::class.java.simpleName
    var timeLeftOnTimer: Long = 10000

    companion object {
        private val SCORE_KEY = "SCORE_KEY"
        private val TIME_LEFT_KEY = "TIME_LEFT_KEY"
        var score1: Any? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        Log.d(TAG, "onCreate called. Score is: $score")

        tapMeButton = findViewById<Button>(id.tap_me_button)
        gameScoreTextView = findViewById<TextView>(id.game_score_text_view)
        timeLeftTextView = findViewById<TextView>(id.time_left_text_view)

       var mp:MediaPlayer =MediaPlayer.create(this, raw.sound_click)

        if (savedInstanceState != null){
            score = savedInstanceState.getInt(SCORE_KEY)
            timeLeftOnTimer = savedInstanceState.getLong(TIME_LEFT_KEY)
            restoreGame()
        } else {
            resetGame()
        }
        tapMeButton.setOnClickListener{ view ->
            val bounceAnimation = AnimationUtils.loadAnimation(this, anim.bounce)
            view.startAnimation(bounceAnimation)
            mp.start()
            incrementScore()
        }
    }
    private fun restoreGame(){
        gameScoreTextView.text = getString(string.sua_pontuacao, score.toString())
        val restoredTime = timeLeftOnTimer/1000
        timeLeftTextView.text=getString(string.time_left, restoredTime.toString())

        countDownTimer= object : CountDownTimer(timeLeftOnTimer, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftOnTimer=millisUntilFinished
                var timeLeft = millisUntilFinished/1000
                timeLeftTextView.text=getString(string.time_left, timeLeft.toString())
            }
            override fun onFinish() {
                endGame()
            }
        }
        countDownTimer.start()
        gameStarted=true
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(SCORE_KEY,score)
        outState.putLong(TIME_LEFT_KEY, timeLeftOnTimer)
        countDownTimer.cancel()
        Log.d(TAG, "onSaveInstanceState: Saving Score: $score & Time Left: $timeLeftOnTimer")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called.")
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.getItemId()
        if (id == R.id.record) {
            val intent = Intent(this, RecordActivity::class.java)
            startActivity(intent)
            return true
        }
        if (id == R.id.about) {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
      fun resetGame(){
        score = 0
        gameScoreTextView.text = getString(string.sua_pontuacao, score.toString())
        val initialTimeLeft = initialCountDown / 1000
        timeLeftTextView.text = getString(string.time_left, initialTimeLeft.toString())

        countDownTimer = object: CountDownTimer(initialCountDown, countDownInterval){
            override fun onTick(millisUntilFinished: Long){
                timeLeftOnTimer=millisUntilFinished
                val timeLeft = millisUntilFinished / 1000
                timeLeftTextView.text = getString(string.time_left, timeLeft.toString())
            }
            override fun onFinish(){
                endGame()
            }
        }
        gameStarted = false
    }
    private fun startGame(){
        countDownTimer.start()
        gameStarted = true
    }
    private fun endGame(){
        val intent = Intent(baseContext, PontuacaoActivity::class.java)
        score1=score
        startActivity(intent)
        //Toast.makeText(this, getString(R.string.game_over_message,score.toString()), Toast.LENGTH_SHORT).show()
        resetGame()
    }
    private fun incrementScore(){
        if(!gameStarted){
            startGame()
        }
        score = score+1
        val newScore = getString(string.sua_pontuacao, score.toString())
        gameScoreTextView.text = newScore
        val blinkAnimation = AnimationUtils.loadAnimation(this, anim.blink)
        gameScoreTextView.startAnimation(blinkAnimation)
    }
}
