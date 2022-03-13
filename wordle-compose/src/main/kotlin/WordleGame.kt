import java.io.File
import kotlin.random.Random


class WordleGame(val wordLength: Int, val maxGuesses: Int) { // , goalWordsFile: String?) {

    // constructor(wordLength: Int, maxGuesses: Int) : this(wordLength, maxGuesses, null)

    constructor() : this(wordLength = 5, maxGuesses = 6)

    companion object {

        const val PATH_TO_RESOURCES = "./src/main/resources"

        val goalWords5 = listOf(
            "hello", "crane", "words", "doubt", "tango", "audio", "disco", "event", "house", "zebra", "robot", "eight",
            "table", "chalk", "music", "world", "jelly", "sound", "chest", "stair", "chess", "horse", "sheep", "stand",
            "funky", "dance", "month", "peace", "apple", "money", "earth", "light", "yield", "steer", "spear", "piece",
            "crate", "chain", "laser", "movie", "count", "grown", "sport", "build", "charm", "spare", "three", "trunk",
            "night", "dwarf", "giant", "under", "break", "night", "mouse", "train", "track", "stack", "white", "black",
            "brown", "green", "noise", "brain", "topic", "phase", "pizza", "queue", "valve", "stamp", "young", "brick"
        )

        fun String.letterCount() = this.fold(initial = mutableMapOf<Char, Int>(), operation = { m, c ->
            m[c] = m.getOrDefault(key = c, defaultValue = 0) + 1
            m
        })
    }

    // init {
    // private val goalWords1 = File("$PATH_TO_RESOURCES/goalwords.txt").readLines().filter { it.isNotBlank() }
    private val goalWords by lazy {
        val goalFile = File("$PATH_TO_RESOURCES/goalwords.txt")
        if (goalFile.canRead())
            goalFile.readLines().filter { it.isNotBlank() }
        else
            goalWords5
    }
    // }

    // val goal = goalWords5[Random.nextInt(goalWords5.size)]
    val goal = goalWords[Random.nextInt(goalWords.size)]

    /**
     * a list of all guesses as regular strings
     */
    val guesses = mutableListOf<String>()

    /**
     * a list of all matches for each guess
     */
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
