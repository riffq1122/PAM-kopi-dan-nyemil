package lat.pam.kopidannyemil.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.UUID

enum class DrinkSize(val label: String, val extraPrice: Int) {
    S("S", 0),
    M("M", 3000),
    L("L", 6000)
}

fun formatRupiah(amount: Int): String =
    "Rp %,d".format(amount).replace(',', '.')

data class CartItem(
    val id: String = UUID.randomUUID().toString(),
    val menu: MenuItem,
    val size: DrinkSize = DrinkSize.M,
    var quantity: Int = 1
) {
    val unitPrice: Int
        get() = menu.price + if (menu.hasSize) size.extraPrice else 0

    val subtotal: Int
        get() = unitPrice * quantity
}

object CartState {
    var selectedDetailItem by mutableStateOf<MenuItem?>(null)
    var selectedSize by mutableStateOf(DrinkSize.M)
    var selectedQuantity by mutableStateOf(1)

    val items = mutableStateListOf<CartItem>()

    val totalItemCount: Int
        get() = items.sumOf { it.quantity }

    val total: Int
        get() = items.sumOf { it.subtotal }

    fun addToCart(menu: MenuItem, size: DrinkSize, quantity: Int) {
        val existingIndex = items.indexOfFirst { it.menu.name == menu.name && (!menu.hasSize || it.size == size) }
        if (existingIndex != -1) {
            val existing = items[existingIndex]
            items[existingIndex] = existing.copy(quantity = existing.quantity + quantity)
        } else {
            items.add(CartItem(menu = menu, size = size, quantity = quantity))
        }
    }

    fun updateQuantity(cartItem: CartItem, newQuantity: Int) {
        val index = items.indexOfFirst { it.id == cartItem.id }
        if (index != -1) {
            if (newQuantity <= 0) {
                items.removeAt(index)
            } else {
                items[index] = items[index].copy(quantity = newQuantity)
            }
        }
    }

    fun removeItem(cartItem: CartItem) {
        items.removeAll { it.id == cartItem.id }
    }

    fun clear() {
        items.clear()
        selectedDetailItem = null
        selectedSize = DrinkSize.M
        selectedQuantity = 1
    }
}
