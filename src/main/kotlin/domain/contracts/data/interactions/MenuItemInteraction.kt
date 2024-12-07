package de.enderkatze.domain.contracts.data.interactions

import de.enderkatze.domain.contracts.data.enums.MenuItemInteractionType
import org.bukkit.entity.Player

data class MenuItemInteraction(
    val actionType: MenuItemInteractionType,
    val player: Player
)