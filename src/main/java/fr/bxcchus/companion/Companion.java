package fr.bxcchus.companion;

import fr.bxcchus.companion.commands.CommandCompanion;

import fr.bxcchus.companion.listeners.CompanionInteractListener;
import fr.bxcchus.companion.objects.CompanionManager;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public final class Companion extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("Plugin Companion activé !");

        NPCRegistry registry = CitizensAPI.getNPCRegistry();
        CompanionManager companionManager = new CompanionManager(registry, this);
        getCommand("companion").setExecutor(new CommandCompanion(companionManager));
        getServer().getPluginManager().registerEvents(new CompanionInteractListener(), this);


    }

    @Override
    public void onDisable() {
        System.out.println("Plugin Companion désactivé !");
    }
}
