import java.io.File


object WordleGame { // , goalWordsFile: String?) {

    // constructor(wordLength: Int, maxGuesses: Int) : this(wordLength, maxGuesses, null)

    // constructor() : this(wordLength = 5, maxGuesses = 6)


        private const val PATH_TO_RESOURCES = "./src/main/resources"

        private val goalWords5Fallback = listOf(
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

        val goalWords by lazy {
            val goalFile = File("$PATH_TO_RESOURCES/goalwords.txt")
            if (goalFile.canRead())
                goalFile.readLines().filter { it.isNotBlank() }
            else
                goalWords5Fallback
        }

    // init {
    // private val goalWords1 = File("$PATH_TO_RESOURCES/goalwords.txt").readLines().filter { it.isNotBlank() }
    // }

    // val goal = goalWords5[Random.nextInt(goalWords5.size)]

    /*
     * a list of all guesses as regular strings
     */
    // var guesses by rememberSaveable { mutableStateOf(listOf<String>()) }
    // val guesses = mutableListOf<String>()

    /*
     * a list of all matches for each guess
     */
    // var matches: List<Array<Match>> by rememberSaveable { mutableStateOf(listOf<Array<Match>>()) }
    //val matches = mutableListOf<Array<Match>>()




}


