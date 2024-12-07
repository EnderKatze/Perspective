package de.enderkatze.domain.contracts.annotations.menu_item_ext

import de.enderkatze.domain.contracts.MenuReference
import de.enderkatze.domain.contracts.annotations.Menu
import de.enderkatze.domain.contracts.annotations.MenuItem
import de.enderkatze.domain.contracts.annotations.menu_item_ext.MenuItemAction
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

@Menu(title = "", rows = 1)
class Example {

    @MenuItem(closeOnUse = true)
    class ExampleMenuItem(menuReference: MenuReference) {

        @MenuItemMaterial
        fun whatever(): ItemStack {

            return ItemStack(Material.DIAMOND)
        }

        @MenuItemAction
        fun action() {
            return
        }
    }
}