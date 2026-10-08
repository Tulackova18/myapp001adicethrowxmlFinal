package com.example.myapp001adicethrowxml

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Nastavení okrajů podle systémových lišt
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.llMain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left + 24,
                systemBars.top + 24,
                systemBars.right + 24,
                systemBars.bottom + 24
            )

            insets
        }

        // Prvky z XML
        val tvDice = findViewById<TextView>(R.id.tvDice)
        val btnRoll = findViewById<Button>(R.id.btnRoll)
        val btnClear = findViewById<Button>(R.id.btnClear)

        val tvRollCount = findViewById<TextView>(R.id.tvRollCount)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        val tvHighest = findViewById<TextView>(R.id.tvHighest)
        val tvStatistics = findViewById<TextView>(R.id.tvStatistics)
        val tvHistory = findViewById<TextView>(R.id.tvHistory)

        // Symboly kostky
        val diceSymbols = listOf(
            "⚀",
            "⚁",
            "⚂",
            "⚃",
            "⚄",
            "⚅"
        )

        // Historie hodů
        val history = mutableListOf<Int>()

        // Počet výskytů jednotlivých čísel
        val statistics = IntArray(6)

        // Kliknutí na tlačítko HODIT
        btnRoll.setOnClickListener {

            lifecycleScope.launch {

                // Během animace nelze znovu kliknout
                btnRoll.isEnabled = false

                // Animace kostky
                repeat(10) {
                    tvDice.text = diceSymbols.random()
                    delay(250)
                }

                // Výsledný náhodný hod
                val diceValue = (1..6).random()

                // Zobrazení výsledku
                tvDice.text = diceSymbols[diceValue - 1]

                // Přidání výsledku do historie
                history.add(diceValue)

                // Aktualizace statistiky
                statistics[diceValue - 1]++

                // Počet hodů
                tvRollCount.text = "Počet hodů: ${history.size}"

                // Celkový součet
                val total = history.sum()
                tvTotal.text = "Celkový součet: $total"

                // Nejvyšší hod
                val highest = history.maxOrNull() ?: 0
                tvHighest.text = "Nejvyšší hod: $highest"

                // Aktualizace statistiky
                tvStatistics.text = """
                    1: ${statistics[0]}×
                    2: ${statistics[1]}×
                    3: ${statistics[2]}×
                    4: ${statistics[3]}×
                    5: ${statistics[4]}×
                    6: ${statistics[5]}×
                """.trimIndent()

                // Aktualizace historie
                tvHistory.text = history.joinToString("   ")

                // Znovu povolit tlačítko
                btnRoll.isEnabled = true
            }
        }

        // Kliknutí na tlačítko VYMAZAT
        btnClear.setOnClickListener {

            // Vymazání historie
            history.clear()

            // Vynulování statistiky
            statistics.fill(0)

            // Vynulování informací
            tvRollCount.text = "Počet hodů: 0"
            tvTotal.text = "Celkový součet: 0"
            tvHighest.text = "Nejvyšší hod: 0"

            tvStatistics.text = """
                1: 0×
                2: 0×
                3: 0×
                4: 0×
                5: 0×
                6: 0×
            """.trimIndent()

            tvHistory.text = "Zatím žádné hody"

            // Vrátit kostku na začátek
            tvDice.text = "⚀"
        }
    }
}