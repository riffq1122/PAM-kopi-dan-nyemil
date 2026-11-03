package lat.pam.kopidannyemil.screens

import android.widget.Toast
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lat.pam.kopidannyemil.data.CartState
import lat.pam.kopidannyemil.data.DrinkSize
import lat.pam.kopidannyemil.data.dummyMenu
import lat.pam.kopidannyemil.data.formatRupiah
import lat.pam.kopidannyemil.ui.theme.BorderGray
import lat.pam.kopidannyemil.ui.theme.Brown
import lat.pam.kopidannyemil.ui.theme.Caramel
import lat.pam.kopidannyemil.ui.theme.Cream
import lat.pam.kopidannyemil.ui.theme.Ink
import lat.pam.kopidannyemil.ui.theme.KopiDanNyemilTheme
import lat.pam.kopidannyemil.ui.theme.Peach

@Composable
fun DetailScreen(
    onBackClick: () -> Unit,
    onBuyClick: () -> Unit
) {
    val context = LocalContext.current
    val menu = CartState.selectedDetailItem ?: dummyMenu.first()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .systemBarsPadding()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        // 1. Top Bar dengan tombol kembali & judul "Detail"
        AppTopBar(
            onBackClick = onBackClick,
            title = "Detail"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 2. Kotak Foto Placeholder
        if (menu.imageRes != null) {
            androidx.compose.foundation.Image(
                painter = androidx.compose.ui.res.painterResource(id = menu.imageRes),
                contentDescription = menu.name,
                contentScale = androidx.compose.ui.layout.ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Peach),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Foto ${menu.name}",
                    color = Brown,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 3. Nama Menu & Deskripsi
        Text(
            text = menu.name,
            color = Ink,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = menu.description,
            color = Color.Gray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider(color = BorderGray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(20.dp))

        // 4. Pilihan Ukuran (S / M / L) -> HANYA tampil kalau menu.hasSize == true
        if (menu.hasSize) {
            Text(
                text = "Ukuran",
                color = Ink,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                DrinkSize.entries.forEach { drinkSize ->
                    SizeChip(
                        size = drinkSize,
                        isSelected = CartState.selectedSize == drinkSize,
                        onClick = { CartState.selectedSize = drinkSize },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        // 5. Pengatur Jumlah (Stepper - / +)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Jumlah",
                color = Ink,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                QuantityButton(
                    symbol = "−",
                    enabled = CartState.selectedQuantity > 1,
                    onClick = {
                        if (CartState.selectedQuantity > 1) CartState.selectedQuantity--
                    }
                )
                Text(
                    text = CartState.selectedQuantity.toString(),
                    color = Ink,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                QuantityButton(
                    symbol = "+",
                    enabled = true,
                    onClick = { CartState.selectedQuantity++ }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // 6. Bar Bawah: Subtotal Harga & Tombol Action (+ Keranjang & Pesan Sekarang)
        val unitPrice = menu.price + if (menu.hasSize) CartState.selectedSize.extraPrice else 0
        val displayPrice = unitPrice * CartState.selectedQuantity

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Harga Subtotal",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    text = formatRupiah(displayPrice),
                    color = Ink,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SecondaryButton(
                    text = "+ Keranjang",
                    onClick = {
                        CartState.addToCart(menu, CartState.selectedSize, CartState.selectedQuantity)
                        Toast.makeText(context, "${menu.name} ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.weight(1f)
                )

                PrimaryButton(
                    text = "Pesan Sekarang",
                    onClick = {
                        CartState.addToCart(menu, CartState.selectedSize, CartState.selectedQuantity)
                        onBuyClick()
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun SizeChip(
    size: DrinkSize,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val shape = RoundedCornerShape(12.dp)
    val bgColor = if (isSelected) Color(0xFFFFF5EE) else Color.White
    val borderColor = if (isSelected) Caramel else BorderGray
    val textColor = if (isSelected) Caramel else Ink

    Column(
        modifier = modifier
            .pressScale(interactionSource)
            .clip(shape)
            .background(bgColor)
            .border(1.5.dp, borderColor, shape)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = size.label,
            color = textColor,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
        if (size.extraPrice > 0) {
            Text(
                text = "+${formatRupiah(size.extraPrice)}",
                color = textColor.copy(alpha = 0.8f),
                fontSize = 11.sp
            )
        } else {
            Text(
                text = "Standar",
                color = Color.Gray,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
private fun QuantityButton(
    symbol: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val borderColor = if (enabled) Caramel else BorderGray
    val textColor = if (enabled) Caramel else Color.LightGray

    Box(
        modifier = Modifier
            .size(36.dp)
            .pressScale(interactionSource)
            .clip(CircleShape)
            .background(Color.White)
            .border(1.5.dp, borderColor, CircleShape)
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol,
            color = textColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailScreenPreview() {
    KopiDanNyemilTheme {
        DetailScreen(
            onBackClick = {},
            onBuyClick = {}
        )
    }
}
