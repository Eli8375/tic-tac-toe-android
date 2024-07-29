package com.example.tic_tac_toe

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {

    private val player1 = Player('X', false, null, 5)
    private val player2 = Player('O', true, null, 4)

    private lateinit var player1WinsTextView: TextView
    private lateinit var player2WinsTextView: TextView
    private lateinit var playerWinsAlertTextView: TextView

    private lateinit var cell1: Button
    private lateinit var cell2: Button
    private lateinit var cell3: Button
    private lateinit var cell4: Button
    private lateinit var cell5: Button
    private lateinit var cell6: Button
    private lateinit var cell7: Button
    private lateinit var cell8: Button
    private lateinit var cell9: Button

    private var displayLock: Boolean = false


    val myGame = Game(player1, player2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        player1WinsTextView = findViewById<TextView>(R.id.player_1_score)
        player2WinsTextView = findViewById<TextView>(R.id.player_2_score)
        playerWinsAlertTextView = findViewById<TextView>(R.id.player_wins_alert)

        cell1 = findViewById(R.id.cell1)
        cell2 = findViewById(R.id.cell2)
        cell3 = findViewById(R.id.cell3)
        cell4 = findViewById(R.id.cell4)
        cell5 = findViewById(R.id.cell5)
        cell6 = findViewById(R.id.cell6)
        cell7 = findViewById(R.id.cell7)
        cell8 = findViewById(R.id.cell8)
        cell9 = findViewById(R.id.cell9)


        cell1.setOnClickListener { onCellClick(it) }
        cell2.setOnClickListener { onCellClick(it) }
        cell3.setOnClickListener { onCellClick(it) }
        cell4.setOnClickListener { onCellClick(it) }
        cell5.setOnClickListener { onCellClick(it) }
        cell6.setOnClickListener { onCellClick(it) }
        cell7.setOnClickListener { onCellClick(it) }
        cell8.setOnClickListener { onCellClick(it) }
        cell9.setOnClickListener { onCellClick(it) }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // keep track of players' wins
        player1WinsTextView.text = "Score: ${player1.wins}"
        player2WinsTextView.text = "Score: ${player2.wins}"


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun onCellClick(view: View) {
        // Extract row and column indices from the view's ID
        val row = (view.id - R.id.cell1) / 3
        println("row: $row")
        val col = (view.id - R.id.cell1) % 3
        println("column: $col")

        myGame.playerChoose(row, col)
        val clickedCell = view as Button
        val cellContent = myGame.getCellValue(row, col)
        if (!displayLock) {
            clickedCell.text = cellContent.toString()
        }
        if (myGame.isLocked()) {
            if (this.player1.justWon) playerWinsAlertTextView.text = "Player 1 Wins!"
            else if (this.player2.justWon) playerWinsAlertTextView.text = "Player 2 Wins!"
            displayLock = true
        }

        player1WinsTextView.text = "Score: ${player1.wins}"
        player2WinsTextView.text = "Score: ${player2.wins}"

    }

    fun clearDisplay() {
        cell1.text = ""
        cell2.text = ""
        cell3.text = ""
        cell4.text = ""
        cell5.text = ""
        cell6.text = ""
        cell7.text = ""
        cell8.text = ""
        cell9.text = ""
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // Determine which menu option was chosen
        return when (item.itemId) {
            R.id.play_again_bar -> {
                myGame.resetGame()
                clearDisplay()

                player1WinsTextView.text = "Score: ${player1.wins}"
                player2WinsTextView.text = "Score: ${player2.wins}"

                displayLock = false
                playerWinsAlertTextView.text = ""
                true
            }
            R.id.reset_score_bar -> {
                myGame.resetScore()
                clearDisplay()

                player1WinsTextView.text = "Score: ${player1.wins}"
                player2WinsTextView.text = "Score: ${player2.wins}"

                displayLock = false
                playerWinsAlertTextView.text = ""
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}