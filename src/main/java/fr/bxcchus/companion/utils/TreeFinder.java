package fr.bxcchus.companion.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

public class TreeFinder {
    private final JavaPlugin plugin;
    public TreeFinder(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void findNearestTreeAsync(Location location, Consumer<Block> callback) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            Block nearestTree = findNearestTree(location);
            callback.accept(nearestTree);
        });
    }

    private Block findNearestTree(Location location) {
        Block nearestTree = null;
        double nearestDistance = Double.MAX_VALUE;
        World world = location.getWorld();
        int radius = 10;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Block block = world.getBlockAt(location.getBlockX() + x, location.getBlockY() + y, location.getBlockZ() + z);
                    if (isLog(block.getType())) {
                        double distance = location.distance(block.getLocation());
                        if (distance < nearestDistance) {
                            nearestDistance = distance;
                            nearestTree = block;
                        }
                    }
                }
            }
        }
        return nearestTree;
    }


    private boolean isLog(Material material) {
        return material == Material.OAK_LOG || material == Material.BIRCH_LOG || material == Material.SPRUCE_LOG || material == Material.JUNGLE_LOG || material == Material.ACACIA_LOG || material == Material.DARK_OAK_LOG;
    }
}
