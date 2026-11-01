package lat.pam.kopidannyemil.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lat.pam.kopidannyemil.ui.theme.Caramel
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
                val period = size.height
                val shift = progress * period * 2f
                drawRect(
                    brush = Brush.linearGradient(
                        colors = listOf(DarkBg, GradientBrown),
                        start = Offset(0f, shift),
                        end = Offset(0f, shift + period),
                        tileMode = TileMode.Mirror
                    )
                )
            }
            .systemBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(Caramel),
            contentAlignment = Alignment.Center
        ) {
            Text("Logo", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Kopi dan Nyemil", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Get a taste of your wish", color = Color.White.copy(alpha = 0.8f))

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(text = "Start now", onClick = onStartClick)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    KopiDanNyemilTheme {
        SplashScreen(onStartClick = {})
    }
}