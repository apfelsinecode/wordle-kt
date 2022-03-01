import kotlin.random.Random


class WordleGame(val wordLength: Int, val maxGuesses: Int) {

    constructor() : this(wordLength = 5, maxGuesses = 6)

    companion object {

        val goalWords5 = listOf(
            "hello", "crane", "words", "doubt", "tango", "audio", "disco", "event", "house", "zebra",
            "table", "chalk", "music", "world", "japan", "korea", "china", "swiss", "chess", "horse", "sheep", "stand",
            "funky", "dance", "month", "peace", "apple", "money", "earth", "light", "yield", "steer", "spear", "piece",
            "crate", "chain", "laser", "movie", "count", "grown", "sport", "build", "charm", "spare", "three", "trunk"
        )

        fun String.letterCount() = this.fold(initial = mutableMapOf<Char, Int>(), operation = { m, c ->
            m[c] = m.getOrDefault(key = c, defaultValue = 0) + 1
            m
        })
    }

    val goal = goalWords5[Random.nextInt(goalWords5.size)]
    val guesses = mutableListOf<String>()
    val matches = mutableListOf<Array<Match>>()


    fun enterGuess(guess: String): GameState? {

        val letters = guess.toCharArray()

        if (letters.size == this.wordLength) {

            guesses.add(guess)

            val letterCount = goal.letterCount()
            val result = Array(size = letters.size, init = { Match.GRAY } )
            letters.forEachIndexed { index, c ->
                if (goal[index] == c) {
                    result[index] = Match.GREEN
                    letterCount[c] = letterCount.getOrDefault(c, 0) - 1
                }
            }

            letters.forEachIndexed { index, c ->
                val count = letterCount[c]
                if (result[index] == Match.GRAY && count != null && count > 0) {
                    result[index] = Match.YELLOW
                    letterCount[c] = count - 1
                }
            }
            matches.add(result)
            return if (guess == goal) GameState.WON
                else if (guesses.size == maxGuesses) GameState.LOST
                else GameState.ENTER_WORD

        } else {
            return null
        }

    }

}


enum class Match(val emoji: String) {
    GRAY(emoji = "⬜"), YELLOW(emoji = """🔶"""), GREEN(emoji = "✅");

}

enum class GameState {
    ENTER_WORD, WON, LOST
}
