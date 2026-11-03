package lat.pam.kopidannyemil.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lat.pam.kopidannyemil.ui.theme.DarkBg
import lat.pam.kopidannyemil.ui.theme.GradientBrown
import lat.pam.kopidannyemil.ui.theme.KopiDanNyemilTheme

@Composable
fun SplashScreen(onStartClick: () -> Unit) {
    val transition = rememberInfiniteTransition(label = "splash")
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 6000, easing = LinearEasing)
        ),
        label = "progress"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                val periodX = size.width
                val periodY = size.height
                val shiftX = progress * periodX * 2f
                val shiftY = progress * periodY * 2f
                drawRect(
                    brush = Brush.linearGradient(
                        colors = listOf(DarkBg, GradientBrown),
                        start = Offset(shiftX, shiftY),
                        end = Offset(shiftX + periodX, shiftY + periodY),
                        tileMode = TileMode.Mirror
                    )
                )
            }
            .systemBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        androidx.compose.foundation.Image(
            painter = androidx.compose.ui.res.painterResource(id = lat.pam.kopidannyemil.R.drawable.logo),
            contentDescription = "Logo Kopi dan Nyemil",
            modifier = Modifier.size(110.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text("Kopi dan Nyemil", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("ngopi kok ga nyemil", color = Color.White.copy(alpha = 0.8f))

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(text = "mulai sekarang", onClick = onStartClick)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    KopiDanNyemilTheme {
        SplashScreen(onStartClick = {})
    }
}