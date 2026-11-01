package lat.pam.kopidannyemil.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lat.pam.kopidannyemil.data.MenuItem
import lat.pam.kopidannyemil.data.UserSession
import lat.pam.kopidannyemil.data.dummyMenu
import lat.pam.kopidannyemil.navigation.Routes
import lat.pam.kopidannyemil.ui.theme.Brown
import lat.pam.kopidannyemil.ui.theme.Caramel
import lat.pam.kopidannyemil.ui.theme.Cream
import lat.pam.kopidannyemil.ui.theme.DarkBg
import lat.pam.kopidannyemil.ui.theme.Ink
import lat.pam.kopidannyemil.ui.theme.KopiDanNyemilTheme
import lat.pam.kopidannyemil.ui.theme.Peach

@Composable
fun HomeScreen(
    onMenuClick: (MenuItem) -> Unit,
    onOrderClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    DarkBg,
                    RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                )
                .statusBarsPadding()
                .padding(horizontal = 24.dp, vertical = 24.dp)
        ) {
            Text("Selamat datang", color = Color(0xFFB7B7B7), fontSize = 13.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Halo ${UserSession.name.ifBlank { "Nama" }},",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            "Makanan & Minuman",
            color = Ink,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(dummyMenu) { menu ->
                MenuCard(
                    menu = menu,
                    onClick = { onMenuClick(menu) }
                )
            }
        }

        AppBottomBar(
            selected = Routes.HOME,
            onHomeClick = {},
            onOrderClick = onOrderClick,
            onProfileClick = onProfileClick
        )
    }
}

@Composable
private fun MenuCard(
    menu: MenuItem,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(16.dp)
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .pressScale(interactionSource, pressedScale = 0.97f)
            .shadow(2.dp, shape)
            .background(Color.White, shape)
            .clip(shape)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Peach),
            contentAlignment = Alignment.Center
        ) {
            Text("Foto", color = Brown, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(menu.name, color = Ink, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(menu.description, color = Color.Gray, fontSize = 13.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text(menu.priceText, color = Caramel, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    KopiDanNyemilTheme {
        HomeScreen(
            onMenuClick = {},
            onOrderClick = {},
            onProfileClick = {}
        )
    }
}