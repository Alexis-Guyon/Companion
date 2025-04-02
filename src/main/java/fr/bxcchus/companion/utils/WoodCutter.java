package fr.bxcchus.companion.utils;

import fr.bxcchus.companion.objects.CompanionInventoryTrait;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;


public class WoodCutter {
    private final JavaPlugin plugin;
    private final CompanionInventoryTrait inventoryTrait;

    public WoodCutter(JavaPlugin plugin, CompanionInventoryTrait inventoryTrait) {
        this.plugin = plugin;
        this.inventoryTrait = inventoryTrait;
    }

    public void chopTree(Block tree) {
        if (tree == null || !isLog(tree.getType())) return;
        simulateSwingAnimation(tree.getWorld(), tree.getLocation());

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (inventoryTrait.getInventory().firstEmpty() != -1) {
                addWoodToInventory(tree.getType());
            } else {
                tree.getWorld().dropItemNaturally(tree.getLocation(), new ItemStack(tree.getType()));
            }
            tree.setType(Material.AIR);
        }, 100L);

    }
    private void addWoodToInventory(Material type) {
        if (inventoryTrait.getInventory().firstEmpty() == -1) {
            return;
        } else {
            inventoryTrait.getInventory().addItem(new ItemStack(type));
        }
    }

    private void simulateSwingAnimation(World world, Location location) {
        world.playEffect(location, Effect.CLICK1, 1);
    }

    private boolean isLog(Material material) {
        return switch (material) {
            case OAK_LOG, BIRCH_LOG, SPRUCE_LOG, JUNGLE_LOG, ACACIA_LOG, DARK_OAK_LOG -> true;
            default -> false;
        };
    }

}
