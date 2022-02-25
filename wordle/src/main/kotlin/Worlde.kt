

fun wordle() {

    val wordLength = 5
    val amountGuesses = 6
    val guesses = (1..amountGuesses)
        .map { Guess(" ".repeat(wordLength)) } //wordLength) }
        .toTypedArray()

    //val goal = Word("hello")

    println("Enter goal:")
    val goal = readLine()!!

    while (true) {

        val input = readLine()

        if (input == null || input == "") break

        val guess = Guess(input)
        println(guess.getMatches(goal)?.joinToString())

    }

    /*for (guess in guesses) {

        val input = readLine()
        if (input?.length == wordLength) {
            guess.letters.indices.forEach { guess.letters[it] = input[it] }
        }

    }*/



}

fun main() {
    wordle()
}

fun String.letterCount() = this.fold(initial = mutableMapOf<Char, Int>(), operation = { m, c ->
    m[c] = m.getOrDefault(key = c, defaultValue = 0) + 1
    m
})

class Word(private val word: String) {

    val letterCount = word.fold(initial = mutableMapOf<Char, Int>(), operation = { m, c ->
        m[c] = m.getOrDefault(key = c, defaultValue = 0) + 1
        m
    })

    override fun equals(other: Any?): Boolean {
        return other is Word && other.word == this.word
    }

    override fun hashCode(): Int {
        return word.hashCode()
    }

    override fun toString(): String = word


}

class Guess(word: String) {

    private val letters: CharArray = word.toCharArray()

    override fun toString(): String {
        return String(letters)
    }

    fun getMatches(goal: String): Array<Match>? {
        if (letters.size == goal.length) {
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

            return result
        } else {
            return null
        }
    }

}

enum class Match {
    GRAY, YELLOW, GREEN;

}
