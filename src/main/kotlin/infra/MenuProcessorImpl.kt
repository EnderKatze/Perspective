package de.enderkatze.infra

import de.enderkatze.domain.contracts.manager.ItemManager
import de.enderkatze.domain.contracts.manager.MenuManager
import de.enderkatze.domain.contracts.MenuProcessor
import de.enderkatze.domain.contracts.annotations.Menu
import de.enderkatze.domain.contracts.annotations.MenuItem
import de.enderkatze.domain.contracts.data.MenuData

class MenuProcessorImpl(
    private val menuManager: MenuManager,
    private val itemManager: ItemManager,
): MenuProcessor {

    override fun registerMenu(menu: Any): Boolean {
        val menuAnnotation: Menu? = menu.javaClass.getAnnotation(Menu::class.java)

        if(menuAnnotation != null) {
            val items = getItems(menu.javaClass)

            val itemIds: List<String> = assignItemsToId(items)

            menuManager.addMenu(menu, getMenuData(menu.javaClass, itemIds))
            return true
        }
        return false
    }

    private fun getMenuData(menu: Class<*>, itemIds: List<String>): MenuData {
        val menuAnnotation: Menu = menu.getAnnotation(Menu::class.java)

        val menuTitle: String = menuAnnotation.title
        val menuRows: Int = menuAnnotation.rows

        return MenuData(menuTitle, menuRows, itemIds)


    }

    private fun assignItemsToId(items: List<Class<*>>): List<String> {
        val itemIds: List<String> = mutableListOf()

        items.forEach({item ->
            val id: String = itemManager.addItem(item)
            itemIds.plus(id)
        })

        return itemIds
    }

    fun getItems(menu: Class<*>): List<Class<*>> {

        val items: List<Class<*>> = mutableListOf()

        for (potentialItem in menu.declaredClasses) {
            if(potentialItem.annotations.contains(MenuItem())) {
                items.plus(potentialItem)
            }
        }
        return items;
    }

}