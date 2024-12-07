package de.enderkatze.domain.contracts.manager

import de.enderkatze.domain.contracts.data.MenuData

interface MenuManager {

    fun addMenu(menu: Any, menuData: MenuData)
    fun getMenu(name: String): Any?
    fun getMenuData(name: String): MenuData?
    fun getMenus(): Map<String, Any>


}