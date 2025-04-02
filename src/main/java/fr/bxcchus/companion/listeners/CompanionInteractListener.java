package fr.bxcchus.companion.listeners;

import fr.bxcchus.companion.objects.CompanionInventoryTrait;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;

public class CompanionInteractListener implements Listener {

    @EventHandler
    public void onNPCInteract(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        Entity clickedEntity = event.getRightClicked();
        NPC npc = CitizensAPI.getNPCRegistry().getNPC(clickedEntity);

        if (npc != null && npc.hasTrait(CompanionInventoryTrait.class)) {
            CompanionInventoryTrait trait = npc.getTrait(CompanionInventoryTrait.class);
            Inventory companionInventory = trait.getInventory();
            player.openInventory(companionInventory);
        }
    }
}
