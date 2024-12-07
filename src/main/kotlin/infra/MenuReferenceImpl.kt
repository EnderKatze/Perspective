package de.enderkatze.infra

import de.enderkatze.domain.contracts.MenuReference
import de.enderkatze.domain.contracts.data.MenuData

class MenuReferenceImpl(
    val currentMenu: String
): MenuReference {
    override fun openNestedMenu(menuName: String) {
        TODO("Not yet implemented")
    }

    override fun closeMenu() {
        TODO("Not yet implemented")
    }

    override fun redrawMenu() {
        TODO("Not yet implemented")
    }

    override fun getOrigin(): MenuData? {
        TODO("Not yet implemented")
    }
}