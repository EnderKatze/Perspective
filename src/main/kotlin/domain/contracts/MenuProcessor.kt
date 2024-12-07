package de.enderkatze.domain.contracts

import de.enderkatze.domain.contracts.annotations.Menu
import de.enderkatze.domain.contracts.data.MenuData

interface MenuProcessor {

    fun registerMenu(menu: Any): Boolean
}