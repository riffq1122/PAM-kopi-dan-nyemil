package lat.pam.kopidannyemil.data

import androidx.annotation.DrawableRes
import lat.pam.kopidannyemil.R

data class MenuItem(
    val name: String,
    val description: String,
    val price: Int,
    val hasSize: Boolean = true,
    @DrawableRes val imageRes: Int? = null
) {
    val priceText: String
        get() = "Rp %,d".format(price).replace(',', '.')
}

val dummyMenu = listOf(
    MenuItem("Kopi Susu Gula Aren", "Espresso, susu, gula aren", 22000, imageRes = R.drawable.kopi_susu_gula_aren),
    MenuItem("Donat Cokelat", "Donat empuk, glaze cokelat", 12000, hasSize = false, imageRes = R.drawable.donat_cokelat),
    MenuItem("Sourdough Loaf", "Roti fermentasi alami", 35000, hasSize = false, imageRes = R.drawable.sourdough_loaf),
    MenuItem("Cappuccino", "Espresso, foam susu lembut", 25000, imageRes = R.drawable.cappuccino),
    MenuItem("Matcha Latte", "Matcha Jepang, susu segar", 27000, imageRes = R.drawable.matcha_latte),
    MenuItem(name="Roti Jala", description = "Makanan khas Melayu", price = 15000, hasSize = false, imageRes = R.drawable.roti_jala)
)