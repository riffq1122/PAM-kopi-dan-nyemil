package lat.pam.kopidannyemil.data

data class MenuItem(
    val name: String,
    val description: String,
    val price: Int,
    val hasSize: Boolean = true
) {
    val priceText: String
        get() = "Rp %,d".format(price).replace(',', '.')
}

val dummyMenu = listOf(
    MenuItem("Kopi Susu Gula Aren", "Espresso, susu, gula aren", 22000),
    MenuItem("Donat Cokelat", "Donat empuk, glaze cokelat", 12000, hasSize = false),
    MenuItem("Sourdough Loaf", "Roti fermentasi alami", 35000, hasSize = false),
    MenuItem("Cappuccino", "Espresso, foam susu lembut", 25000),
    MenuItem("Matcha Latte", "Matcha Jepang, susu segar", 27000),
    MenuItem(name="puding pak hambali", description = "puding kenyal kenyal :p", price = 0, hasSize = false)
)