package dev.hawu.plugins.playerprofiles
package commands

import dev.hawu.plugins.api.*
import dev.hawu.plugins.api.commands.*
import dev.hawu.plugins.playerprofiles.I18n.*

class ReloadCommand extends AbstractCommandClass("reload") :

    allowPlayers()
    allowConsole()

    setPermission("ppf.reload")

    override def run(sender: CommandSource, args: CommandArgument): Unit =
        Constants.getPlugin.reloadConfig()
        I18n.reload
        sender.sendMessage("reloaded".tl())

    override def tab(sender: CommandSource, args: CommandArgument): java.util.List[String] = null
