package com.undefined.api.builder

import com.undefined.api.UndefinedUtils
import com.undefined.api.util.component
import com.undefined.api.util.legacyString
import com.undefined.api.util.miniMessage
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import java.util.*
import kotlin.collections.HashMap

@Suppress("UNCHECKED_CAST")
class ItemBuilder(private val material: Material) {

    private var name: Component? = null
    private var lore: MutableList<Component> = mutableListOf()
    private var amount: Int = 1
    private var customModelData: Int = 0
    private var persistentDataContainers: HashMap<NamespacedKey, PDCInfo<*, *>> = hashMapOf()
    private var enchantments: HashMap<Enchantment, Int> = HashMap()
    private var unsafeEnchantments: HashMap<Enchantment, Int> = HashMap()
    private var unbreakable: Boolean = false
    private var skullOwner: UUID? = null
    private var skullTexture: String? = null
    private var flags: MutableList<ItemFlag> = mutableListOf()
    private var attributeModifiers: HashMap<Attribute, AttributeModifier> = hashMapOf()

    fun setName(name: Component): ItemBuilder {
        this.name = name
        return this
    }

    fun setName(name: String): ItemBuilder {
        this.name = "<reset>$name".miniMessage()
        return this
    }

    fun setPlainName(name: String): ItemBuilder {
        this.name = name.component()
        return this
    }

    fun setLore(lore: List<Component>): ItemBuilder {
        this.lore = lore.toMutableList()
        return this
    }

    fun setLore(vararg lore: Component): ItemBuilder {
        this.lore = lore.toMutableList()
        return this
    }

    fun setLore(vararg lore: String): ItemBuilder {
        this.lore = lore.map { "<reset>$it".miniMessage() }.toMutableList()
        return this
    }

    fun addLore(vararg lore: Component): ItemBuilder {
        this.lore.addAll(lore.toList())
        return this
    }

    fun addLore(vararg lore: String): ItemBuilder {
        this.lore.addAll(lore.map { "<reset>$it".miniMessage() }.toList())
        return this
    }

    fun addPlainLore(vararg lore: String): ItemBuilder {
        this.lore.addAll(lore.map { it.component() }.toList())
        return this
    }

    fun setAmount(amount: Int): ItemBuilder {
        this.amount = amount
        return this
    }

    fun addAmount(amount: Int): ItemBuilder {
        this.amount += amount
        return this
    }

    fun setCustomModelData(customModelData: Int): ItemBuilder {
        this.customModelData = customModelData
        return this
    }

    fun <P, C : Any> addPersistentDataType(key: NamespacedKey, persistentDataType: PersistentDataType<P, C>, value: C): ItemBuilder {
        this.persistentDataContainers[key] = PDCInfo(persistentDataType, value)
        return this
    }

    fun addEnchantment(enchantment: Enchantment, level: Int = 1): ItemBuilder {
        this.enchantments[enchantment] = level
        return this
    }

    fun setEnchantments(enchantments: HashMap<Enchantment, Int>): ItemBuilder {
        this.enchantments = enchantments
        return this
    }

    fun addUnsafeEnchantment(enchantment: Enchantment, level: Int = 1): ItemBuilder {
        this.unsafeEnchantments[enchantment] = level
        return this
    }

    fun setUnsafeEnchantments(enchantments: HashMap<Enchantment, Int>): ItemBuilder {
        this.unsafeEnchantments = enchantments
        return this
    }

    fun setUnbreakable(unbreakable: Boolean): ItemBuilder {
        this.unbreakable = unbreakable
        return this
    }

    fun setSkullOwner(skullOwner: UUID): ItemBuilder {
        this.skullOwner = skullOwner
        return this
    }

    fun setSkullTexture(skullTexture: String): ItemBuilder {
        this.skullTexture = skullTexture
        return this
    }

    fun addFlag(flag: ItemFlag): ItemBuilder {
        this.flags.add(flag)
        return this
    }

    fun addFlags(vararg flag: ItemFlag): ItemBuilder {
        this.flags.addAll(flag)
        return this
    }

    fun addFlags(flag: List<ItemFlag>): ItemBuilder {
        this.flags.addAll(flag)
        return this
    }

    fun setFlags(flags: List<ItemFlag>): ItemBuilder {
        this.flags = flags.toMutableList()
        return this
    }

    fun addAttributeModifier(attribute: Attribute, modifier: AttributeModifier): ItemBuilder {
        this.attributeModifiers[attribute] = modifier
        return this
    }

    fun addAttributeModifiers(attributeModifiers: HashMap<Attribute, AttributeModifier>): ItemBuilder {
        for ((attribute, modifier) in attributeModifiers) this.attributeModifiers[attribute] = modifier
        return this
    }

    fun setAttributeModifiers(attributeModifiers: HashMap<Attribute, AttributeModifier>): ItemBuilder {
        this.attributeModifiers = attributeModifiers
        return this
    }

    fun build(): ItemStack {
        val item = ItemStack(material)
        item.addEnchantments(enchantments)
        item.addUnsafeEnchantments(unsafeEnchantments)
        item.amount = this.amount

        val meta = item.itemMeta ?: return item

        meta.setDisplayName(this.name?.legacyString())
        meta.lore = this.lore.map { it.legacyString() }
        meta.setCustomModelData(this.customModelData)
        for ((key, container) in persistentDataContainers)
            meta.persistentDataContainer[key, container.type as PersistentDataType<Any, Any>] = container.value
        meta.isUnbreakable = this.unbreakable
        meta.addItemFlags(*this.flags.toTypedArray())
        for ((attribute, modifier) in attributeModifiers) meta.addAttributeModifier(attribute, modifier)

        item.itemMeta = meta
        return item
    }

}