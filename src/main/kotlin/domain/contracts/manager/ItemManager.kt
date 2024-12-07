package de.enderkatze.domain.contracts.manager

import org.bukkit.inventory.ItemStack

interface ItemManager {
    fun getItem(id: String): Any?
    fun getItemMenu(itemId: String): String?
    fun getItems(): Map<String, Any>
    fun addItem(itemClass: Class<*>): String
}