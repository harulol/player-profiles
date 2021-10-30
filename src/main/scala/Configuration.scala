package dev.hawu.plugins.playerprofiles

import dev.hawu.plugins.api.*
import dev.hawu.plugins.api.collections.tuples.*
import dev.hawu.plugins.api.inventories.item.*
import dev.hawu.plugins.playerprofiles.RandomUtils.*
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

    def init(pl: JavaPlugin): Unit =
        plugin = Some(pl)
        pl.saveDefaultConfig()

    def getItemTemplate(player: Player, registrar: Player, duration: String = "1mo"): ItemStack =
        val config = plugin.get.getConfig
        val paras = Seq(
            "player's display name" -> player.getDisplayName,
            "player's custom name" -> player.getCustomName,
            "player's name" -> player.getName,
            "player's uuid" -> player.getUniqueId.toString,
            "registrar's display name" -> (if player.getUniqueId.equals(registrar.getUniqueId) then "Self-Issued" else registrar.getDisplayName),
            "registrar's custom name" -> (if player.getUniqueId.equals(registrar.getUniqueId) then "Self-Issued" else registrar.getCustomName),
            "registrar's name" -> (if player.getUniqueId.equals(registrar.getUniqueId) then "Self-Issued" else registrar.getName),
            "registration date" -> LocalDateTime.now().format(dateTimeFormatter),
            "expiry" -> TimeConversions.convertToReadableFormat(
                TimeConversions.convertToMillis(duration),
                TimeUnit.SECOND,
                true,
                true,
            ),
        ).map(_.toPair)

        val material = Material.getMaterial(config.getString("id-template.material").toUpperCase.replace(' ', '_')) ?: Material.PAPER
        val lore = config.getStringList("id-template.lore").asScala.toSeq ?: Seq()
        val displayName = Strings.fillPlaceholders(config.getString("id-template.display-name"), paras: _*)

        ItemStackBuilder().material(material).withMeta().displayName(displayName).withLore().add(lore: _*).loopAndFill(paras: _*).build().build().build()
