package de.enderkatze.domain.contracts.manager

import de.enderkatze.domain.contracts.MenuReference

interface MenuReferenceManager {

    fun getMenuReference(menu: String): MenuReference

    fun setMenuReference(menu: String, menuReference: MenuReference)
}