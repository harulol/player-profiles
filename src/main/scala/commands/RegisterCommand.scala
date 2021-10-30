package dev.hawu.plugins.playerprofiles
package commands

import dev.hawu.plugins.api.commands.*
import dev.hawu.plugins.playerprofiles.*
import dev.hawu.plugins.playerprofiles.I18nImpl.*
import dev.hawu.plugins.playerprofiles.RandomUtils.*
import org.bukkit.*

class RegisterCommand extends AbstractCommandClass("register") :

    allowPlayers()
    setPermission("ppf.register")

    def run(sender: CommandSource, args: CommandArgument): Unit =
        val player = args.getPlayer(0)
        val duration = args.get(1) ?: "1mo"
        if player == null then
            return sender.tl("player-not-found")
        val leftovers = sender.getPlayer.getInventory.addItem(Configuration.getItemTemplate(player, sender.getPlayer, duration))

        if !leftovers.isEmpty then
            sender.tl("full-inventory")

    // TODO: Add tab completions
    def tab(sender: CommandSource, args: CommandArgument): java.util.List[String] = null
