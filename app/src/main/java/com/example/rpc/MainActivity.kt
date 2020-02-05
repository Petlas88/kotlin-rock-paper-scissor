package com.example.rpc

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var scoreToWin: Int = 3

    enum class Hand { ROCK, PAPER, SCISSORS, BLANK }


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
        if (p1.ready) {
            p1_paper.visibility = View.INVISIBLE
            p1_rock.visibility = View.INVISIBLE
            p1_scissor.visibility = View.INVISIBLE
            p1_random.visibility = View.INVISIBLE
        }

        if (p2.ready) {
            p2_paper.visibility = View.INVISIBLE
            p2_rock.visibility = View.INVISIBLE
            p2_scissor.visibility = View.INVISIBLE
            p2_random.visibility = View.INVISIBLE
        }





        if (p1.ready && p2.ready) {
            calculateRoundResult(p1, p2)
            println("${p1.wins}:${p2.wins}")
            when (p1.hand) {
                Hand.ROCK -> p1_hand.setImageResource(R.drawable.rock)
                Hand.PAPER -> p1_hand.setImageResource(R.drawable.paper)
                Hand.SCISSORS -> p1_hand.setImageResource(R.drawable.scissor)

            }

            when (p2.hand) {
                Hand.ROCK -> p2_hand.setImageResource(R.drawable.rock)
                Hand.PAPER -> p2_hand.setImageResource(R.drawable.paper)
                Hand.SCISSORS -> p2_hand.setImageResource(R.drawable.scissor)
            }

            next_btn.visibility = View.VISIBLE
            next_btn.setOnClickListener() {
                p1_paper.visibility = View.VISIBLE
                p1_rock.visibility = View.VISIBLE
                p1_scissor.visibility = View.VISIBLE
                p2_paper.visibility = View.VISIBLE
                p2_rock.visibility = View.VISIBLE
                p2_scissor.visibility = View.VISIBLE
                p1_random.visibility = View.VISIBLE
                p2_random.visibility = View.VISIBLE
                p2_hand.setImageDrawable(null)
                p1_hand.setImageDrawable(null)
                next_btn.visibility = View.INVISIBLE
            }

            if (p1.wins == scoreToWin) {
                p1.winner = true
                showWinner(p1, p2)

            } else if (p2.wins == scoreToWin) {
                p2.winner = true
                showWinner(p1, p2)

            }
            p1_score.text = p1.wins.toString()
            p2_score.text = p2.wins.toString()
            p1.ready = false
            p2.ready = false
        } else {
            println("Both no ready")
        }
    }

    private fun calculateRoundResult(p1: Player, p2: Player) {

        if (p1.hand == p2.hand) {
            println("Tie!")
        } else {
            when (p1.hand) {
                Hand.PAPER -> if (p2.hand == Hand.ROCK) p1.wins++ else p2.wins++
                Hand.ROCK -> if (p2.hand == Hand.SCISSORS) p1.wins++ else p2.wins++
                Hand.SCISSORS -> if (p2.hand == Hand.PAPER) p1.wins++ else p2.wins++
            }
        }
    }

    private fun getRandomHand(): Hand {
        return when (Random.nextInt(0, 2)) {
            0 -> Hand.SCISSORS
            1 -> Hand.ROCK
            2 -> Hand.PAPER
            else -> Hand.PAPER
        }
    }

    private fun showWinner(p1: Player, p2: Player) {
        win_txt.visibility = View.VISIBLE
        p1_score.visibility = View.INVISIBLE
        p2_score.visibility = View.INVISIBLE
        score_dash.visibility = View.INVISIBLE
        again_button.visibility = View.VISIBLE

        again_button.setOnClickListener {
            p1.wins = 0
            p2.wins = 0
            p1_score.text = p1.wins.toString()
            p2_score.text = p2.wins.toString()
            win_txt.visibility = View.INVISIBLE
            p1_score.visibility = View.VISIBLE
            p2_score.visibility = View.VISIBLE
            score_dash.visibility = View.VISIBLE
            p1_paper.visibility = View.VISIBLE
            p1_rock.visibility = View.VISIBLE
            p1_scissor.visibility = View.VISIBLE
            p2_paper.visibility = View.VISIBLE
            p2_rock.visibility = View.VISIBLE
            p2_scissor.visibility = View.VISIBLE
            p1_random.visibility = View.VISIBLE
            p2_random.visibility = View.VISIBLE
            again_button.visibility = View.INVISIBLE
        }
        if (p1.winner) {
            win_txt.text = "Blue wins!"
            win_txt.setTextColor(Color.rgb(127, 143, 250))
        }
        if (p2.winner) {
            win_txt.text = "Red wins!"
            win_txt.setTextColor(Color.rgb(253, 116, 109))
        }
    }

}
