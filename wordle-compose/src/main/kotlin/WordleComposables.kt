import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        modifier = Modifier.background(
            color = matchColor?.toColor() ?: Color.LightGray,
            shape = RoundedCornerShape(10.dp),
        ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            fontSize = 10.sp
        )
    }
}

@Preview
@Composable
fun letterA() = letter("A")

@Composable
fun letterRow(text: String) {
    Row {
        text.toCharArray().map {
            letter(text = it.toString())
        }
    }
}
