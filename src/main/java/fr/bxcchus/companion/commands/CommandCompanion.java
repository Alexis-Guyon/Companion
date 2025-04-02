package fr.bxcchus.companion.commands;

import fr.bxcchus.companion.objects.CompanionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCompanion implements CommandExecutor {
    private final CompanionManager companionManager;

    public CommandCompanion(CompanionManager companionManager) {
        this.companionManager = companionManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Seuls les joueurs peuvent invoquer un compagnon !");
            return false;
        }
        companionManager.summonCompanion(player);
        return true;
    }
}
