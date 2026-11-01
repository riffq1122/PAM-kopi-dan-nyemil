package lat.pam.kopidannyemil.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lat.pam.kopidannyemil.ui.theme.Cream
import lat.pam.kopidannyemil.ui.theme.KopiDanNyemilTheme

@Composable
fun RegisterScreen(onRegisterClick: (String) -> Unit, onBackClick: () -> Unit) {
    var fullName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .systemBarsPadding()
            .padding(24.dp)
    ) {
        AppTopBar(onBackClick = onBackClick)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Register", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(32.dp))

        AppTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = "nama lengkap"
        )
        Spacer(modifier = Modifier.height(12.dp))
        AppTextField(
            value = username,
            onValueChange = { username = it },
            label = "username"
        )
        Spacer(modifier = Modifier.height(12.dp))
        AppTextField(
            value = password,
            onValueChange = { password = it },
            label = "password",
            isPassword = true
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = "Register",
            onClick = { onRegisterClick(fullName) },
            enabled = fullName.isNotBlank() && username.isNotBlank() && password.isNotBlank()
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    KopiDanNyemilTheme {
        RegisterScreen(onRegisterClick = {}, onBackClick = {})
    }
}