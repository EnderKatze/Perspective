package de.enderkatze.domain.contracts.data

import org.bukkit.event.inventory.InventoryAction

data class InteractionData(
    val clickType: InventoryAction,
)