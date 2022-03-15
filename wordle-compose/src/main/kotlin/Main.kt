import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {

    
    MaterialTheme {
        Column {

            var wordleState by rememberSaveable { mutableStateOf(WordleState()) }

            gameBoard(wordleState) { guess: String ->
                wordleState = wordleState.enterGuess(guess)
            }
        }


    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Wordle Compose") {
        App()
    }
}
