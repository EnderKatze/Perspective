package de.enderkatze.domain.contracts.data

import org.bukkit.Material

data class MenuItemData(
    val title: String,
    val material: Material,
    val lore: Array<String> = arrayOf(),
    val closeOnUse: Boolean = true,
    val action: (() -> Unit)? = null
)