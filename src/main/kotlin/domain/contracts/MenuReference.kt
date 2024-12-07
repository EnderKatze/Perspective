package de.enderkatze.domain.contracts

import de.enderkatze.domain.contracts.data.MenuData

interface MenuReference {

    fun openNestedMenu(menuName: String)
    fun closeMenu()
    fun redrawMenu()
    fun getOrigin(): MenuData?
}