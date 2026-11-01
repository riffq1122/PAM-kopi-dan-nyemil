package lat.pam.kopidannyemil.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lat.pam.kopidannyemil.data.CartState
import lat.pam.kopidannyemil.data.UserSession
import lat.pam.kopidannyemil.data.formatRupiah
import lat.pam.kopidannyemil.ui.theme.BorderGray
import lat.pam.kopidannyemil.ui.theme.Caramel
import lat.pam.kopidannyemil.ui.theme.Cream
import lat.pam.kopidannyemil.ui.theme.DarkGreen
import lat.pam.kopidannyemil.ui.theme.Ink
import lat.pam.kopidannyemil.ui.theme.KopiDanNyemilTheme
import lat.pam.kopidannyemil.ui.theme.Mint

@Composable
fun ConfirmScreen(onOkClick: () -> Unit) {
    val buyerName = UserSession.name.ifBlank { "Kak" }
    val cartItems = CartState.items
    val totalPaid = CartState.total

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .systemBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(Mint),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "OK",
                color = DarkGreen,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Pesanan Berhasil!",
            color = Ink,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Terima kasih $buyerName, pesananmu sedang kami siapkan dan akan segera dikirim ke alamat tujuan.",
            color = Color.Gray,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        if (cartItems.isNotEmpty()) {
            Spacer(modifier = Modifier.height(24.dp))
            val shape = RoundedCornerShape(16.dp)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(2.dp, shape)
                    .background(Color.White, shape)
                    .padding(16.dp)
            ) {
                cartItems.forEachIndexed { index, cartItem ->
                    val sizeText = if (cartItem.menu.hasSize) " (${cartItem.size.label})" else ""
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${cartItem.quantity}x ${cartItem.menu.name}$sizeText",
                            color = Ink,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = formatRupiah(cartItem.subtotal),
                            color = Ink,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    if (index < cartItems.size - 1) {
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(color = BorderGray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total Dibayar",
                        color = Ink,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = formatRupiah(totalPaid),
                        color = Caramel,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = "Kembali ke Beranda",
            onClick = onOkClick
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ConfirmScreenPreview() {
    KopiDanNyemilTheme {
        ConfirmScreen(onOkClick = {})
    }
}
