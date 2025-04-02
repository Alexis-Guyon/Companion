package fr.bxcchus.companion.objects;

import net.citizensnpcs.api.trait.Trait;
import org.bukkit.inventory.Inventory;

public class CompanionInventoryTrait extends Trait {
    private final Inventory inventory;
    public CompanionInventoryTrait(Inventory inventory) {
        super("companion_inventory");
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory.clear();
        this.inventory.setContents(inventory.getContents());

    }
}
