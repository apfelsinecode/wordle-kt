import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


fun Match.toColor(): Color {
    return when (this) {
        Match.GRAY -> Color.DarkGray
        Match.GREEN -> Color.Green
        Match.YELLOW -> Color.Yellow
    }
}

@Composable
fun letter(text: String, matchColor: Match? = null) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(
                color = matchColor?.toColor() ?: Color.LightGray,
                shape = RoundedCornerShape(10.dp),
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 50.sp
        )
    }
}

@Composable
fun letterRow(text: String, matches: Array<Match>) {
    val matchRow = matches // ?: arrayOfNulls(text.length)
    Row {
        (text.toCharArray() zip matchRow).map {
                Box(modifier = Modifier.padding(all = 2.dp)){
                    letter(text = it.first.toString(), matchColor = it.second)
                }
            }
    }
}

@Composable
fun letterTable(words: List<String>, matches: List<Array<Match>>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        words.indices.map {
            val word = words[it]
            val matchRow = matches[it] // ?.get(it)  ?: arrayOfNulls(word.length)
            letterRow(word, matchRow)
        }
    }
}

@Composable
fun gameBoard(wordleGame: WordleState, onClickEnter: (String) -> Unit) {
    // var gameState by rememberSaveable { mutableStateOf<GameStatus?>(null) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Wordle")
        Text(text = wordleGame.gameStatus.toString())
        val words = wordleGame.guesses
        val matches = wordleGame.matches
        letterTable(words = words, matches = matches)
        guessTextField { onClickEnter(it) }
    }
}

@Preview
@Composable
fun guessTextField(onClickEnter: (String) -> Unit) {
    var guess by rememberSaveable { mutableStateOf("") }

    Row {
        TextField(
            value = guess,
            onValueChange = { guess = it },
            singleLine = true
        )
        Button(onClick = { onClickEnter(guess) }) {
            Text("enter")
        }
    }
}

@Preview
@Composable
fun helloWorld() {
    letterTable(listOf("HELLO", "WORLD"), listOf(
        arrayOf(
            Match.GREEN, Match.GREEN, Match.GREEN, Match.GREEN, Match.GREEN
        ),
        arrayOf(
            Match.YELLOW, Match.YELLOW, Match.YELLOW, Match.YELLOW, Match.YELLOW
        )
    ))
}