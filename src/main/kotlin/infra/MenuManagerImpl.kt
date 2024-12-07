package de.enderkatze.infra

import de.enderkatze.domain.contracts.manager.MenuManager
import de.enderkatze.domain.contracts.annotations.Menu
import de.enderkatze.domain.contracts.data.MenuData

class MenuManagerImpl: MenuManager {

    private val menus: MutableMap<String, Any> = mutableMapOf()

    private val menusData: MutableMap<String, MenuData> = mutableMapOf()

    override fun addMenu(menu: Any, menuData: MenuData) {
        menus[menu.javaClass.getAnnotation(Menu::class.java).title] = menu
    }

    override fun getMenu(name: String): Any? {
        return menus[name]
    }

    override fun getMenuData(name: String): MenuData? {
        return menusData[name]
    }

    override fun getMenus(): Map<String, Any> {
        return menus
    }
}