package dev.hawu.plugins.playerprofiles

import dev.hawu.plugins.api.collections.tuples.*
import dev.hawu.plugins.api.inventories.item.*
import dev.hawu.plugins.api.*
import org.bukkit.*
import org.bukkit.configuration.file.*
import org.bukkit.entity.*
import org.bukkit.inventory.*
import org.bukkit.plugin.java.*

import java.time.*
import java.time.format.*
import scala.jdk.CollectionConverters.*

object Configuration:

    private var plugin: Option[JavaPlugin] = None
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a")

    extension[T] (any: T)
        def ?:(other: T): T =
            return if any == null then other else any

    def init(pl: JavaPlugin): Unit =
        plugin = Some(pl)
        pl.saveDefaultConfig()

    def getItemTemplate(player: Player, registrar: Player, duration: String = "1mo"): ItemStack =
        val config = plugin.get.getConfig
        val params = Seq(
            Pair("player's display name", player.getDisplayName),
            Pair("player's custom name", player.getCustomName),
            Pair("player's name", player.getName),
            Pair("player's uuid", player.getUniqueId.toString),
            Pair("registrar's display name", (if player.getUniqueId.equals(registrar.getUniqueId) then "Self-Issued" else registrar.getDisplayName)),
            Pair("registrar's custom name", (if player.getUniqueId.equals(registrar.getUniqueId) then "Self-Issued" else registrar.getCustomName)),
            Pair("registrar's name", (if player.getUniqueId.equals(registrar.getUniqueId) then "Self-Issued" else registrar.getName)),
            Pair("registration date", LocalDateTime.now().format(dateTimeFormatter)),
            Pair("expiry", TimeConversions.convertToReadableFormat(
                TimeConversions.convertToMillis(duration),
                TimeUnit.SECOND,
                true,
                true,
            )),
        )

        val material = Material.getMaterial(config.getString("id-template.material").toUpperCase.replace(' ', '_')) ?: Material.PAPER
        val lore = config.getStringList("id-template.lore").asScala.toSeq ?: Seq()
        val displayName = Strings.fillPlaceholders(config.getString("id-template.display-name"), params: _*)
        return ItemStackBuilder().material(material).withMeta().displayName(displayName).withLore().add(lore: _*).loopAndFill(params: _*).build().build().build()
