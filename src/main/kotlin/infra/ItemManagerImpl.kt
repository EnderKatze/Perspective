package de.enderkatze.infra

import de.enderkatze.domain.contracts.manager.ItemManager
import de.enderkatze.domain.contracts.manager.MenuManager
import de.enderkatze.domain.contracts.manager.MenuReferenceManager
import de.enderkatze.domain.contracts.data.MenuData
import java.util.UUID

class ItemManagerImpl(
    private val menuReferenceManager: MenuReferenceManager,
    private val menuManager: MenuManager,
): ItemManager {

    private val items: MutableMap<String, Class<*>> = mutableMapOf()

    override fun getItem(id: String): Any? {

        val menu: String? = getItemMenu(id)

        if(menu != null) {
            return items[id]?.getDeclaredConstructor()?.newInstance(menuReferenceManager.getMenuReference(menu))
        }
        return null
    }

    override fun getItemMenu(itemId: String): String? {
        val menuDatas: Map<String, MenuData> = menuManager.getMenus().keys.mapNotNull { key ->
            menuManager.getMenuData(key)?.let { key to it }
        }.toMap()

        for (menu: MenuData in menuDatas.values) {
            for(possibleItemId: String in menu.itemIDs) {
                if(possibleItemId == itemId) return menu.title
            }
        }
        return null
    }

    override fun getItems(): Map<String, Any> {
        val itemObjects: MutableMap<String, Any> = mutableMapOf()

        for (item in items.keys) {
            val menu: String? = getItemMenu(item)
            if (menu != null) {
                itemObjects[item] = items[item]
                    ?.getDeclaredConstructor()
                    ?.newInstance(
                        menuReferenceManager
                            .getMenuReference(menu)
                    ) as Any
            }
        }
        return items
    }

    override fun addItem(itemClass: Class<*>): String {
        val itemId = UUID.randomUUID().toString()
        items[itemId] = itemClass

        return itemId
    }
}