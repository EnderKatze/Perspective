package de.enderkatze.infra.gui

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType

class GuiBuilderImpl(
    private val namespacedKey: NamespacedKey,
): GuiBuilder {
    override fun buildGui(title: String, rows: Int, items: Map<String, ItemStack>, originPath: String?): Inventory {
        val inventory = Bukkit.createInventory(null, rows*9+9, title)

        for(i: Int in inventory.size-10..<inventory.size) {
            inventory.setItem(i, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        }

        if(originPath != null) {
            inventory.setItem(inventory.size - 10, getBackButton(originPath))
        }



        for(item: String in items.keys) {
            val meta: ItemMeta? = items[item]!!.itemMeta

            if(meta != null) {
                meta.persistentDataContainer.set(namespacedKey, PersistentDataType.STRING, "item.$item")
                items[item]!!.itemMeta = meta
            } else {
                throw Exception()
            }
        }

        return inventory
    }

    private fun getBackButton(originPath: String): ItemStack {
        // TODO Find good item
        val backButton = ItemStack(Material.OAK_SIGN)

        val itemMeta: ItemMeta? = backButton.itemMeta
        itemMeta?.persistentDataContainer?.set(namespacedKey, PersistentDataType.STRING, "back.$originPath")

        backButton.itemMeta = itemMeta
        return backButton
    }

}