package dev.hawu.plugins.playerprofiles

import dev.hawu.plugins.api.*
import dev.hawu.plugins.api.commands.*
import org.bukkit.command.*
import org.bukkit.configuration.file.*
import org.bukkit.plugin.java.*

import java.io.*

object I18n:

    private var plugin: Option[JavaPlugin] = None

    private var messages: Option[FileConfiguration] = None
    private var messagesFile: Option[File] = None
    private var defaultMessages: Option[FileConfiguration] = None

    def init(pl: JavaPlugin): Unit =
        plugin = Some(pl)
        messagesFile = Some(File(pl.getDataFolder, "messages.yml"))
        defaultMessages = Some(YamlConfiguration.loadConfiguration(BufferedReader(InputStreamReader(pl.getResource("messages.yml")))))

        if !messagesFile.get.exists() then
            pl.saveResource("messages.yml", true)

        messages = Some(YamlConfiguration.loadConfiguration(messagesFile.get))

    private def getValue(config: FileConfiguration, key: String): String =
        if config.isList(key) then
            return String.join(" ", config.getStringList(key))
        else return config.getString(key)

    def reload: Unit =
        messages = Some(YamlConfiguration.loadConfiguration(messagesFile.get))

    extension (key: String)
        def tl(params: (String, Any)*): String =
            var value: String = ""
            if messages.get.get(key) == null && defaultMessages.get.get(key) == null then
                value = key
            else if messages.get.get(key) == null && defaultMessages.get.get(key) != null then
                messages.get.set(key, defaultMessages.get.get(key))
                value = getValue(defaultMessages.get, key)
            else value = getValue(messages.get, key)

            for param <- params do
                value = value.replace(s"%${param._1}%", if param._2 == null then "null" else param._2.toString)

            return Strings.color(value)

    extension (sender: CommandSender)
        def tl(key: String, params: (String, Any)*): Unit =
            sender.sendMessage(key.tl(params: _*))

    extension (source: CommandSource)
        def tl(key: String, params: (String, Any)*): Unit =
            source.sendMessage(key.tl(params: _*))
