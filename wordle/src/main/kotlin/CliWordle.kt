
fun main() {
    val game = WordleGame()

    println("--- wordle ---")
    println(board(game))
    do {
        val status = game.enterGuess(readGuess(game.wordLength))
        println(board(game))
        when (status) {
            GameState.WON -> println("You won!")
            GameState.LOST -> println("You lost! Word was ${game.goal}")
            else -> {}
        }
    } while (status == GameState.ENTER_WORD)



}

fun readGuess(wordLength: Int): String {
    while (true) {
        print("Enter guess: ")
        val guess = readLine()!!
        if (guess.length == wordLength) {
            return guess
        } else {
            println("Word has to have length $wordLength!")
        }
    }
}

fun board(game: WordleGame): String {
    val result = StringBuilder()
    val width = game.wordLength * 2 + 3
    result.append("-".repeat(width)).append('\n')
    for (index in 0 until game.maxGuesses) {
        result.append("| ")
        result.append(
            game.guesses.getOrElse(index = index, defaultValue = { "_".repeat(game.wordLength) })
                .widen()
        )

        result.append(" |\n|")

        result.append(
            game.matches.getOrNull(index = index)?.joinToString(separator = "") { it.emoji }
                ?: " ".repeat(game.wordLength * 2)
        )

        result.append(" |\n")
    }
    result.append("-".repeat(width))

    return result.toString()
}

private fun String.widen(): String = toCharArray().joinToString(separator = " ")