package com.example.rpc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var scoreToWin:Int = 3
    enum class Hand{ROCK, PAPER, SCISSORS}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val p1 = Player()
        val p2 = Player()

        p1_random.setOnClickListener {
            p1.ready = true
            p1.hand = getRandomHand()
            playHands(p1, p2)
        }
        p2_random.setOnClickListener {
            p2.ready = true
            p2.hand = getRandomHand()
            playHands(p1, p2)
        }
        p1_rock.setOnClickListener {
            p1.ready = true
            p1.hand = Hand.ROCK
            playHands(p1, p2)
        }
        p2_rock.setOnClickListener {
            p2.ready = true
            p2.hand = Hand.ROCK
            playHands(p1, p2)
        }
        p1_paper.setOnClickListener {
            p1.ready = true
            p1.hand = Hand.PAPER
            playHands(p1, p2)
        }
        p2_paper.setOnClickListener {
            p2.ready = true
            p2.hand = Hand.PAPER
            playHands(p1, p2)
        }
        p1_scissor.setOnClickListener {
            p1.ready = true
            p1.hand = Hand.SCISSORS
            playHands(p1, p2)
        }
        p2_scissor.setOnClickListener {
            p2.ready = true
            p2.hand = Hand.SCISSORS
            playHands(p1, p2)
        }

    }



    private fun playHands(p1: Player, p2: Player) {
        if(p1.ready && p2.ready) {
            calculateRoundResult(p1, p2)
            println("${p1.wins}:${p2.wins}")

            if(p1.wins == scoreToWin) {
                println("P1 wins!")
                p1_random.text = "!!"

            } else if(p2.wins == scoreToWin) {
                println("P2 wins!")
                p2_random.text = "!!"

            }
            p1_score.text = p1.wins.toString()
            p2_score.text = p2.wins.toString()
            p1.ready = false
            p2.ready = false
        } else {
            println("Both no ready")
        }
    }
    private fun calculateRoundResult(p1:Player, p2:Player) {

        if (p1.hand == p2.hand) {
            println("Tie!")
        } else {
            when(p1.hand) {
                Hand.PAPER -> if (p2.hand == Hand.ROCK) p1.wins++ else p2.wins++
                Hand.ROCK -> if (p2.hand == Hand.SCISSORS) p1.wins++ else p2.wins++
                Hand.SCISSORS -> if (p2.hand == Hand.PAPER) p1.wins++ else p2.wins++
            }
        }
    }
    private fun getRandomHand():Hand {
        return when(Random.nextInt(0, 2)) {
            0 -> Hand.SCISSORS
            1 -> Hand.ROCK
            2 -> Hand.PAPER
            else -> Hand.PAPER
        }
    }


}
