package lat.pam.kopidannyemil.screens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lat.pam.kopidannyemil.ui.theme.Caramel
import lat.pam.kopidannyemil.ui.theme.Cream
import lat.pam.kopidannyemil.ui.theme.Ink
import lat.pam.kopidannyemil.ui.theme.KopiDanNyemilTheme

@Composable
fun SignUpScreen(
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val loginInteraction = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .systemBarsPadding()
            .padding(24.dp)
    ) {
        AppTopBar(onBackClick = onBackClick)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Sign up", color = Ink, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "Join as a member & experience more fun from various meal at mesen_gube marketing app",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(text = "Register", onClick = onRegisterClick)

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = onLoginClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .pressScale(loginInteraction),
            interactionSource = loginInteraction,
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Caramel),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Caramel)
        ) {
            Text("Login", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

@Preview(name = "Light Mode", showBackground = true, showSystemUi = true, device = Devices.PIXEL_7)
@Preview(name = "Dark Mode", showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_7)
@Composable
fun SignUpScreenPreview() {
    KopiDanNyemilTheme {
        SignUpScreen(onRegisterClick = {}, onLoginClick = {}, onBackClick = {})
    }
}