package de.enderkatze.infra

import de.enderkatze.domain.contracts.ItemProcessor
import de.enderkatze.domain.contracts.annotations.MenuItem
import de.enderkatze.domain.contracts.annotations.menu_item_ext.MenuItemAction
import de.enderkatze.domain.contracts.annotations.menu_item_ext.MenuItemMaterial
import de.enderkatze.domain.contracts.data.InteractionData
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.lang.reflect.Method

class ItemProcessorImpl: ItemProcessor {
    override fun runItemAction(item: Any, interactionData: InteractionData) {
        item.javaClass.declaredMethods.forEach { method: Method ->
            if(method.isAnnotationPresent(MenuItemAction::class.java)) {
                method.invoke(item, interactionData)
            }
        }
    }

    override fun getItemMaterial(item: Any): ItemStack {
        item.javaClass.declaredMethods.forEach { method: Method ->
            if(method.isAnnotationPresent(MenuItemMaterial::class.java)) {
                val value = method.invoke(item)

                return value as ItemStack
            }
        }
        return ItemStack(Material.DIAMOND, 42)
    }

    override fun getAnnotation(item: Any): MenuItem {
        return item.javaClass.getAnnotation(MenuItem::class.java)
    }
}