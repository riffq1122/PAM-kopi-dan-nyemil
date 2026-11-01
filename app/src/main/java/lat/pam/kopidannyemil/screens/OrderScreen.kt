package lat.pam.kopidannyemil.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lat.pam.kopidannyemil.data.CartItem
import lat.pam.kopidannyemil.data.CartState
import lat.pam.kopidannyemil.data.UserSession
import lat.pam.kopidannyemil.data.formatRupiah
import lat.pam.kopidannyemil.navigation.Routes
import lat.pam.kopidannyemil.ui.theme.BorderGray
import lat.pam.kopidannyemil.ui.theme.Caramel
import lat.pam.kopidannyemil.ui.theme.Cream
import lat.pam.kopidannyemil.ui.theme.Ink
import lat.pam.kopidannyemil.ui.theme.KopiDanNyemilTheme

@Composable
fun OrderScreen(
    onOrderClick: () -> Unit,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    var fullName by remember { mutableStateOf(UserSession.name) }
    var address by remember { mutableStateOf("") }
    var detail by remember { mutableStateOf("") }

    val hasItems = CartState.items.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            AppTopBar(
                onBackClick = onBackClick,
                title = "Pengiriman & Pesanan"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ringkasan Pesanan",
                color = Ink,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))

            if (!hasItems) {
                EmptyCartCard(onBrowseMenuClick = onHomeClick)
            } else {
                OrderSummaryCard()
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Alamat Pengiriman",
                color = Ink,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))

            AppTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = "Nama Lengkap"
            )
            Spacer(modifier = Modifier.height(12.dp))
            AppTextField(
                value = address,
                onValueChange = { address = it },
                label = "Alamat Lengkap",
                singleLine = false,
                minLines = 2
            )
            Spacer(modifier = Modifier.height(12.dp))
            AppTextField(
                value = detail,
                onValueChange = { detail = it },
                label = "Patokan atau catatan kurir (opsional)",
                singleLine = false,
                minLines = 2
            )

            Spacer(modifier = Modifier.height(24.dp))

            PrimaryButton(
                text = "Order dan Kirim",
                onClick = onOrderClick,
                enabled = hasItems && fullName.isNotBlank() && address.isNotBlank()
            )
        }

        AppBottomBar(
            selected = Routes.ORDER,
            onHomeClick = onHomeClick,
            onOrderClick = {},
            onProfileClick = onProfileClick
        )
    }
}

@Composable
private fun OrderSummaryCard() {
    val items = CartState.items
    val shape = RoundedCornerShape(16.dp)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, shape)
            .background(Color.White, shape)
            .padding(16.dp)
    ) {
        items.forEachIndexed { index, cartItem ->
            CartItemRow(cartItem = cartItem)
            if (index < items.size - 1) {
                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = BorderGray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        Spacer(modifier = Modifier.height(14.dp))
        HorizontalDivider(color = BorderGray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total Pembayaran",
                color = Ink,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = formatRupiah(CartState.total),
                color = Caramel,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun CartItemRow(cartItem: CartItem) {
    val sizeInfo = if (cartItem.menu.hasSize) " • Ukuran ${cartItem.size.label}" else ""

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = cartItem.menu.name,
                color = Ink,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "${cartItem.quantity}x @ ${formatRupiah(cartItem.unitPrice)}$sizeInfo",
                color = Color.Gray,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = formatRupiah(cartItem.subtotal),
                color = Caramel,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ItemStepperButton(
                symbol = "−",
                onClick = { CartState.updateQuantity(cartItem, cartItem.quantity - 1) }
            )
            Text(
                text = cartItem.quantity.toString(),
                color = Ink,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            ItemStepperButton(
                symbol = "+",
                onClick = { CartState.updateQuantity(cartItem, cartItem.quantity + 1) }
            )
            ItemDeleteButton(
                onClick = { CartState.removeItem(cartItem) }
            )
        }
    }
}

@Composable
private fun ItemStepperButton(
    symbol: String,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .size(30.dp)
            .pressScale(interactionSource)
            .clip(CircleShape)
            .background(Color.White)
            .border(1.dp, Caramel, CircleShape)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol,
            color = Caramel,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ItemDeleteButton(
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .size(30.dp)
            .pressScale(interactionSource)
            .clip(CircleShape)
            .background(Color(0xFFFFF0F0))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "✕",
            color = Color.Red,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun EmptyCartCard(onBrowseMenuClick: () -> Unit) {
    val shape = RoundedCornerShape(16.dp)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape)
            .border(1.dp, BorderGray, shape)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Belum ada menu di keranjang.",
                color = Color.Gray,
                fontSize = 14.sp
            )
            TextButton(onClick = onBrowseMenuClick) {
                Text(
                    text = "Pilih Menu di Home",
                    color = Caramel,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OrderScreenPreview() {
    KopiDanNyemilTheme {
        OrderScreen(
            onOrderClick = {},
            onBackClick = {},
            onHomeClick = {},
            onProfileClick = {}
        )
    }
}
