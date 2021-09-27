package dev.hawu.plugins.playerprofiles
package commands

import dev.hawu.plugins.api.commands.*
import dev.hawu.plugins.playerprofiles.Configuration.*
import dev.hawu.plugins.playerprofiles.I18n.*
import org.bukkit.*

class RegisterCommand extends AbstractCommandClass("register") :

    allowPlayers()
    setPermission("ppf.register")

    def run(sender: CommandSource, args: CommandArgument): Unit =
        val player = args.getPlayer(0)
        val duration = args.get(1) ?: "1mo"
        if player == null then
            return sender.getPlayer.tl("player-not-found")
        val leftovers = sender.getPlayer.getInventory.addItem(Configuration.getItemTemplate(player, sender.getPlayer, duration))

        if !leftovers.isEmpty then
            sender.getPlayer.tl("full-inventory")

    // TODO: Add tab completions
    def tab(sender: CommandSource, args: CommandArgument): java.util.List[String] = null
