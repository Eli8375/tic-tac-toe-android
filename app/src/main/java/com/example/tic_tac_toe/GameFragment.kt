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
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.Fragment

class GameFragment : Fragment() {

    private val player1 = Player('X', false, null, 5)
    private val player2 = Player('O', true, null, 4)

    private lateinit var player1WinsTextView: TextView
    private lateinit var player2WinsTextView: TextView
    private lateinit var playerWinsAlertTextView: TextView
    private lateinit var playAgainButton: Button
    private lateinit var resetScoreButton: Button

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val parentView = inflater.inflate(R.layout.fragment_game, container, false)

        player1WinsTextView = parentView.findViewById<TextView>(R.id.player_1_score)
        player2WinsTextView = parentView.findViewById<TextView>(R.id.player_2_score)
        playerWinsAlertTextView = parentView.findViewById<TextView>(R.id.player_wins_alert)
        playAgainButton = parentView.findViewById<Button>(R.id.play_again)
        resetScoreButton = parentView.findViewById<Button>(R.id.reset_score)

        cell1 = parentView.findViewById(R.id.cell1)
        cell2 = parentView.findViewById(R.id.cell2)
        cell3 = parentView.findViewById(R.id.cell3)
        cell4 = parentView.findViewById(R.id.cell4)
        cell5 = parentView.findViewById(R.id.cell5)
        cell6 = parentView.findViewById(R.id.cell6)
        cell7 = parentView.findViewById(R.id.cell7)
        cell8 = parentView.findViewById(R.id.cell8)
        cell9 = parentView.findViewById(R.id.cell9)


        cell1.setOnClickListener { onCellClick(it) }
        cell2.setOnClickListener { onCellClick(it) }
        cell3.setOnClickListener { onCellClick(it) }
        cell4.setOnClickListener { onCellClick(it) }
        cell5.setOnClickListener { onCellClick(it) }
        cell6.setOnClickListener { onCellClick(it) }
        cell7.setOnClickListener { onCellClick(it) }
        cell8.setOnClickListener { onCellClick(it) }
        cell9.setOnClickListener { onCellClick(it) }

        playAgainButton.setOnClickListener{ playAgain(it) }
        resetScoreButton.setOnClickListener{ resetGame(it) }


        // initialize tracking of players' wins
        player1WinsTextView.text = "Score: ${player1.wins}"
        player2WinsTextView.text = "Score: ${player2.wins}"

        // Inflate the layout for this fragment
        return parentView
    }

    private fun playAgain(view: View) {
        myGame.resetGame()
        clearDisplay()

        player1WinsTextView.text = "Score: ${player1.wins}"
        player2WinsTextView.text = "Score: ${player2.wins}"

        displayLock = false
        playerWinsAlertTextView.text = ""
    }

    private fun resetGame(view: View) {
        myGame.resetScore()
        clearDisplay()

        player1WinsTextView.text = "Score: ${player1.wins}"
        player2WinsTextView.text = "Score: ${player2.wins}"

        displayLock = false
        playerWinsAlertTextView.text = ""
    }

    private fun onCellClick(view: View) {
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

    private fun clearDisplay() {
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

}