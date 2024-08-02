package com.example.tic_tac_toe

class Game(private var player1: Player, private var player2: Player) {
    private var movesAvailable: Int = 9;
    private var locked: Boolean = false
    private var board = arrayOf(
        arrayOf<Char?>('A', 'B', 'C'),
        arrayOf<Char?>('D', 'E', 'F'),
        arrayOf<Char?>('G', 'H', 'I')
    )

    init {
        for (i in board.indices) {
            for (j in board[i].indices) {
                println(board[i][j])
            }
        }
        println(locked)
        println(movesAvailable)
    }

    fun getCellValue(row: Int, col: Int): Char? {
        return board[row][col]
    }

    fun isLocked(): Boolean {
        return locked
    }

    fun playerChoose(k: Int, l: Int) {
        println("k: $k")
        println("l: $l")



        if (!locked) {
            if (board[k][l] != 'X' && board[k][l] != 'O') {
                if (this.player1.playerType == 'X' && !this.player1.tookTurn) {
                    for (i in board.indices) {
                        for (j in board.indices) {
                            if (i == k && j == l) {
                                this.board[i][j] = 'X';
                                break;
                            }
                        }
                    }
                    this.player1.tookTurn = true;
                    this.player2.tookTurn = false;
                    this.player1.turnsLeft--;
                    this.movesAvailable--;
                    println(this.movesAvailable);
                } else {
                    for (i in board.indices) {
                        for (j in board.indices) {
                            if (i == k && j == l) {
                                this.board[i][j] = 'O';
                                break;
                            }
                        }
                    }
                    this.player2.tookTurn = true;
                    this.player1.tookTurn = false;
                    this.player2.turnsLeft--;
                    this.movesAvailable--;
                    println(this.movesAvailable);
                }
            }

            for (i in board.indices) {
                println("${board[i][0]} | ${board[i][1]} | ${board[i][2]}")
            }
            println("Moves Available: $movesAvailable")
            checkForWin()
        }
    }

    fun checkForWin(): Boolean {
        if (this.movesAvailable <= 0) {
            this.gameOver('N');
            return true;
        }
        //top across
        if ((this.board[0][0] == this.board[0][1]) && (this.board[0][1] == this.board[0][2])) {
            if (this.board[0][0] == null || this.board[0][1] == null || this.board[0][2] == null) return false
            else if (this.board[0][2] == 'X') {
                this.gameOver('X');
                return true;
            }
            else {
                this.gameOver('O');
                return true;
            }
        }
        //left down
        else if ((this.board[0][0] == this.board[1][0]) && (this.board[1][0] == this.board[2][0])) {
            if (this.board[0][0] == null || this.board[1][0] == null || this.board[2][0] == null) return false
            else if (this.board[2][0] == 'X') {
                this.gameOver('X');
                return true;
            }
            else {
                this.gameOver('O');
                return true;
            }
        }
        //middle across
        else if ((this.board[1][0] == this.board[1][1]) && (this.board[1][1] == this.board[1][2])) {
            if (this.board[1][0] == null || this.board[1][1] == null || this.board[1][2] == null) return false
            else if (this.board[1][2] == 'X') {
                this.gameOver('X');
                return true;
            }
            else {
                this.gameOver('O');
                return true;
            }
        }
        //right down
        else if ((this.board[0][2] == this.board[1][2]) && (this.board[1][2] == this.board[2][2])) {
            if (this.board[0][2] == null || this.board[1][2] == null || this.board[2][2] == null) return false
            else if (this.board[2][2] == 'X') {
                this.gameOver('X');
                return true;
            }
            else if (this.board[2][2] == null) return false
            else {
                this.gameOver('O');
                return true;
            }
        }
        //down across
        else if ((this.board[2][0] == this.board[2][1]) && (this.board[2][1] == this.board[2][2])) {
            if (this.board[2][0] == null || this.board[2][1] == null || this.board[2][2] == null) return false
            else if (this.board[2][2] == 'X' || this.board[2][1] == 'X' || this.board[2][0] == 'X') {
                this.gameOver('X');
                return true;
            }
            else {
                this.gameOver('O');
                return true;
            }
        }
        //diagonal from left to right
        else if ((this.board[0][0] == this.board[1][1]) && (this.board[1][1] == this.board[2][2])) {
            if (this.board[0][0] == null || this.board[1][1] == null || this.board[2][2] == null) return false
            else if (this.board[2][2] == 'X') {
                this.gameOver('X');
                return true;
            }
            else {
                this.gameOver('O');
                return true;
            }
        }
        //straight down
        else if ((this.board[0][1] == this.board[1][1]) && (this.board[1][1] == this.board[2][1])) {
            if (this.board[0][1] == null || this.board[1][1] == null || this.board[2][1] == null) return false
            else if (this.board[0][1] == 'X') {
                this.gameOver('X');
                return true;
            }
            else {
                this.gameOver('O');
                return true;
            }
        }
        //diagonal from right to left
        else if ((this.board[0][2] == this.board[1][1]) && (this.board[1][1] == this.board[2][0])) {
            if (this.board[0][2] == null || this.board[1][1] == null || this.board[2][0] == null) return false
            else if (this.board[2][0] == 'X') {
                this.gameOver('X');
                return true;
            }
            else {
                this.gameOver('O');
                return true;
            }
        }
        else {
            return false;
        }
    }

    private fun gameOver(playerType: Char?): Boolean {
        if (playerType == 'X') {
            println("Player 1 wins!")
            this.player1.wins++
            this.player1.tookTurn = false
            this.player2.tookTurn = true
            this.locked = true
            player1.justWon = true
            return true
        }
        else if (playerType == 'O') {
            println("Player 2 wins!")
            this.player2.wins++
            this.player1.tookTurn = false
            this.player2.tookTurn = true
            this.locked = true
            player2.justWon = true
            return true
        }
        else if (this.movesAvailable <= 0) {
            this.player1.tookTurn = false
            this.player2.tookTurn = true
            this.locked = true
            println("Tie!")
            return true
        }
        else return false
    }

    fun resetGame() {
        for (i in board.indices) {
            for (j in board[i].indices) {
                this.board[i][j] = ('A' + i * 3 + j).toChar()
            }
        }
        this.player1.turnsLeft = 5
        this.player2.turnsLeft = 4
        this.movesAvailable = 9
        this.locked = false
        player1.justWon = false
        player2.justWon = false
    }

    fun resetScore() {
        player1.wins = 0
        player2.wins = 0
        resetGame()
    }

}