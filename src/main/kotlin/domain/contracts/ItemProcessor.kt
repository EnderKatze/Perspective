package de.enderkatze.domain.contracts

import de.enderkatze.domain.contracts.annotations.MenuItem
import de.enderkatze.domain.contracts.data.InteractionData
import org.bukkit.inventory.ItemStack

interface ItemProcessor {
    fun runItemAction(item: Any, interactionData: InteractionData)
    fun getItemMaterial(item: Any): ItemStack
    fun getAnnotation(item: Any): MenuItem
}