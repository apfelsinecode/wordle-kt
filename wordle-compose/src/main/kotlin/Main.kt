// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Column {
            Button(onClick = {
                text = "Hello, Desktop!"
            }) {
                Text(text)
            }
            Row {
                text.split("").map { letter(it) }
            }
            letterTable(
                words = text.split(" "),
                matches = listOf(
                    arrayOf(Match.GREEN, Match.GREEN, Match.GRAY, Match.GREEN, Match.YELLOW),
                    null,
//                    arrayOf(Match.YELLOW, Match.GRAY, Match.GRAY, Match.GREEN, Match.YELLOW)
                )
            )
        }


    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Wordle Compose") {
        App()
    }
}
