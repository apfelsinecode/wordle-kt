import WordleGame.goalWords
import WordleGame.letterCount
import kotlin.random.Random

enum class Match(val emoji: String) {
    GRAY(emoji = "⬜"), YELLOW(emoji = """🔶"""), GREEN(emoji = "✅");

}

enum class GameStatus {
    ENTER_WORD, WON, LOST, INVALID_GUESS
}

data class WordleState(
    val guesses: List<String> = emptyList(),
    val matches: List<Array<Match>> = emptyList(),
    val goal: String = goalWords[Random.nextInt(goalWords.size)].also { println(it) },
    val gameStatus: GameStatus = GameStatus.ENTER_WORD,
    val wordLength: Int = 5,
    val maxGuesses: Int = 6,
) {

    fun enterGuess(guess: String): WordleState {
        val letters = guess.toCharArray()

        if (letters.size == this.wordLength) {

            val guessesNew = guesses + guess
            // guesses.add(guess)

            val letterCount = goal.letterCount()
            val result: Array<Match> = Array(size = letters.size, init = { Match.GRAY } )
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
            val matchesNew = matches.plusElement(result)


            return if (guess == goal)
                this.copy(guesses = guessesNew, matches = matchesNew, gameStatus = GameStatus.WON)
            else if (guesses.size == maxGuesses)
                this.copy(guesses = guessesNew, matches = matchesNew, gameStatus = GameStatus.LOST)
            else
                this.copy(guesses = guessesNew, matches = matchesNew, gameStatus = GameStatus.ENTER_WORD)

        } else {
            return this.copy(gameStatus = GameStatus.INVALID_GUESS)
        }

    }


}