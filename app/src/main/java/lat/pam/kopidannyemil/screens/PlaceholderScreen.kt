package lat.pam.kopidannyemil.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import lat.pam.kopidannyemil.ui.theme.KopiDanNyemilTheme

@Composable
fun PlaceholderScreen(
    title: String,
    buttons: List<Pair<String, () -> Unit>> = emptyList()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title)
        buttons.forEach { (label, onClick) ->
            Button(onClick = onClick) { Text(label) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceholderScreenPreview() {
    KopiDanNyemilTheme {
        PlaceholderScreen(
            title = "Splash",
            buttons = listOf("Start now" to {})
        )
    }
}