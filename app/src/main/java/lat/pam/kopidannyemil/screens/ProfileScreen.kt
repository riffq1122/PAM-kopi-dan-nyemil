package lat.pam.kopidannyemil.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lat.pam.kopidannyemil.data.UserSession
import lat.pam.kopidannyemil.navigation.Routes
import lat.pam.kopidannyemil.ui.theme.Cream
import lat.pam.kopidannyemil.ui.theme.Ink
import lat.pam.kopidannyemil.ui.theme.KopiDanNyemilTheme

@Composable
fun ProfileScreen(
    onSaveClick: (String) -> Unit,
    onLogoutClick: () -> Unit,
    onHomeClick: () -> Unit,
    onOrderClick: () -> Unit
) {
    val context = LocalContext.current
    var name by remember { mutableStateOf(UserSession.name) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(24.dp)
        ) {
            Text(
                text = "Halo ${UserSession.name.ifBlank { "Nama" }},",
                color = Ink,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text("Profile Saya", color = Ink, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Profile Setting", color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))

            AppTextField(
                value = name,
                onValueChange = { name = it },
                label = "nama"
            )

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                text = "Save",
                onClick = {
                    onSaveClick(name)
                    Toast.makeText(context, "Profil disimpan", Toast.LENGTH_SHORT).show()
                },
                enabled = name.isNotBlank()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onLogoutClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.5.dp, Color(0xFFE53935)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFFE53935)
                )
            ) {
                Text("Logout", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }

        AppBottomBar(
            selected = Routes.PROFILE,
            onHomeClick = onHomeClick,
            onOrderClick = onOrderClick,
            onProfileClick = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    KopiDanNyemilTheme {
        ProfileScreen(
            onSaveClick = {},
            onLogoutClick = {},
            onHomeClick = {},
            onOrderClick = {}
        )
    }
}
