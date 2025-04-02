package fr.bxcchus.companion.utils;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.function.Consumer;

public class CompanionNavigator {
    private final NPC companion;
    private final JavaPlugin plugin;

    public CompanionNavigator(NPC companion, JavaPlugin plugin) {
        this.companion = companion;
        this.plugin = plugin;
    }

    public void moveTo(Location targetLocation, Consumer<Boolean> callback) {
        if (targetLocation != null && companion != null && companion.getNavigator() != null) {
            companion.getNavigator().setTarget(targetLocation);

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (companion.getNavigator().isNavigating() && companion.getEntity().getLocation().distanceSquared(targetLocation) < 4) {
                        callback.accept(true);
                        cancel();
                    } else if (!companion.getNavigator().isNavigating()) {
                        callback.accept(false);
                        cancel();
                    }
                }
            }.runTaskTimer(plugin, 0L, 20L);
        } else {
            callback.accept(false);
        }
    }



}
