package fr.bxcchus.companion.objects;

import fr.bxcchus.companion.ai.CompanionAI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.api.trait.trait.Equipment;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class CompanionManager {
    private final NPCRegistry registry;
    private final JavaPlugin plugin;

    public CompanionManager(NPCRegistry registry, JavaPlugin plugin) {
        this.registry = registry;
        this.plugin = plugin;
    }

    public void summonCompanion(Player player) {
        NPC companion = registry.createNPC(org.bukkit.entity.EntityType.PLAYER, "Compagnon");
        companion.spawn(player.getLocation());
        Equipment equipment = companion.getOrAddTrait(Equipment.class);
        equipment.set(0, new ItemStack(Material.DIAMOND_AXE));

        player.sendMessage("Un compagnon a été invoqué !");

        Inventory companionInventory = plugin.getServer().createInventory(null, 9, "Inventaire du compagnon");
        CompanionInventoryTrait inventoryTrait = new CompanionInventoryTrait(companionInventory);

        companion.addTrait(inventoryTrait);

        inventoryTrait.setInventory(companionInventory);
        new CompanionAI(companion, plugin, inventoryTrait).startWoodCutting();


    }
}
