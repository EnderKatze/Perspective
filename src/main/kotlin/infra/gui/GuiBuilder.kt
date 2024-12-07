package de.enderkatze.infra.gui

import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

interface GuiBuilder {
    fun buildGui(title: String, rows: Int, items: Map<String, ItemStack>, originPath: String?): Inventory
}