package com.example.tic_tac_toe

class Player(var playerType: Char?,
             var tookTurn: Boolean,
             var name: String?,
             var turnsLeft: Int)
{
    var wins: Int = 0;
    var justWon: Boolean = false
}