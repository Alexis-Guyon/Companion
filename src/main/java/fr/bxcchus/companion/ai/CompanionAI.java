package fr.bxcchus.companion.ai;

import fr.bxcchus.companion.objects.CompanionInventoryTrait;
import fr.bxcchus.companion.utils.CompanionNavigator;
import fr.bxcchus.companion.utils.TreeFinder;
import fr.bxcchus.companion.utils.WoodCutter;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class CompanionAI {
    private final NPC companion;
    private final JavaPlugin plugin;
    private final CompanionNavigator navigator;
    private final TreeFinder treeFinder;
    private final WoodCutter woodcutter;

    public CompanionAI(NPC companion, JavaPlugin plugin, CompanionInventoryTrait inventoryTrait) {
        this.companion = companion;
        this.plugin = plugin;
        this.navigator = new CompanionNavigator(companion, plugin);
        this.treeFinder = new TreeFinder(plugin);
        this.woodcutter = new WoodCutter(plugin, inventoryTrait);

    }

    public void startWoodCutting() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Location startLocation = companion.getEntity().getLocation();

                treeFinder.findNearestTreeAsync(startLocation, tree -> {
                    if (tree != null) {
                        Bukkit.getScheduler().runTask(plugin, () -> navigator.moveTo(tree.getLocation(), success -> {
                            if (success) {
                                woodcutter.chopTree(tree);
                            }
                        }));
                    }
                });
            }
        }.runTaskTimer(plugin, 0L, 200L);
    }
}
