package de.enderkatze.infra.listeners

import de.enderkatze.Perspective
import de.enderkatze.domain.contracts.manager.ItemManager
import de.enderkatze.domain.contracts.ItemProcessor
import de.enderkatze.domain.contracts.manager.MenuManager
import de.enderkatze.domain.contracts.data.InteractionData
import de.enderkatze.domain.contracts.manager.DisplayManager
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.persistence.PersistentDataType


class InventoryClickListener(
    private val menuManager: MenuManager,
    private val itemManager: ItemManager,
    private val itemProcessor: ItemProcessor,
    private val namespacedKey: NamespacedKey,
    private val displayManager: DisplayManager,
): Listener {

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {

        if (event.view.title in menuManager.getMenus().keys) {
            event.isCancelled = true

            val menuData = menuManager.getMenuData(event.view.title) ?: return
            var itemId = event.view.cursor?.itemMeta
                ?.persistentDataContainer
                ?.get(namespacedKey, PersistentDataType.STRING)

            if (itemId != null) {
                if(itemId.startsWith("item")) {

                    itemId = itemId.split(".")[1]

                    if (itemId in menuData.itemIDs) {
                        val item = itemManager.getItem(itemId) ?: return

                        if (itemProcessor.getAnnotation(item).closeOnUse) {
                            event.view.close()
                        }

                        itemProcessor.runItemAction(item, InteractionData(event.action))
                    }
                } else if(itemId.startsWith("back")) {
                    val originPath: String = itemId.split(".")[1]
                    val origin: String = originPath.split(",").last()
                    val originTrim = originPath.split(",").dropLast(1).joinToString()

                    displayManager.openMenu(origin, event.view.player as Player, originTrim)


                }
            }
        }
    }
}