package de.enderkatze.domain.contracts.manager

import org.bukkit.entity.Player

interface DisplayManager {

    fun openMenu(menu: String, player: Player, originPath: String? = null)
}