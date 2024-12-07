package de.enderkatze.infra

import de.enderkatze.domain.contracts.ItemProcessor
import de.enderkatze.domain.contracts.data.MenuData
import de.enderkatze.domain.contracts.manager.DisplayManager
import de.enderkatze.domain.contracts.manager.ItemManager
import de.enderkatze.domain.contracts.manager.MenuManager
import de.enderkatze.infra.gui.GuiBuilder
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class DisplayManagerImpl(
    val guiBuilder: GuiBuilder,
    val menuManager: MenuManager,
    val itemManager: ItemManager,
    val itemProcessor: ItemProcessor,

): DisplayManager {
    override fun openMenu(menu: String, player: Player, originPath: String?) {
       val menuData: MenuData? = menuManager.getMenuData(menu)

        if(menuData != null) {
            val items: List<String> = menuData.itemIDs

            val itemMap: MutableMap<String, ItemStack> = mutableMapOf()

            for(item: String in items) {
                val itemStack: ItemStack? = itemManager.getItem(item)?.let { itemProcessor.getItemMaterial(it) }

                if(itemStack != null) {
                    itemMap[item] = itemStack
                }
            }

            val gui: Inventory = guiBuilder.buildGui(menuData.title, menuData.rows, itemMap, originPath)

            player.openInventory(gui)


        }
    }
}