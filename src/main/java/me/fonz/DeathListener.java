package me.fonz;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DeathListener implements Listener {

    private Lifegive plugin;
    private short maxHearts;

    public DeathListener(Lifegive plugin) {
        this.plugin = plugin;
        maxHearts = (short) plugin.getConfig().getInt("max-hearts");
    }

    @EventHandler
    public void kill(PlayerDeathEvent event) {
        Player killed = event.getEntity();
        Player killer = event.getEntity().getKiller();

        // If player has enough hearts to be stolen from
        if (killer != null && killed.getMaxHealth() - 2 > 0) {

            // Make sure killer doesn't get over 24 hearts
            if (killer.getMaxHealth() + 2 <= maxHearts * 2) {
                killed.setMaxHealth(killed.getMaxHealth() - 2);
                killer.setMaxHealth(killer.getMaxHealth() + 2);
            }
        }
    }

    @EventHandler
    public void heart(PlayerInteractEvent event) {
        if(event.getItem() == null) return;
        ItemMeta meta = event.getItem().getItemMeta();
        ItemMeta heartMeta = plugin.getHeartManager().getHeart().getItemMeta();
        if(meta == null || heartMeta == null) return;
        if(meta.getDisplayName().equalsIgnoreCase(heartMeta.getDisplayName()) && event.getItem().getType() == plugin.getHeartManager().getHeart().getType()) {
            if(event.getHand() != null) {
                if (event.getPlayer().getMaxHealth() + 2 <= 24 * 2) {
                    event.setCancelled(true);
                    ItemStack stack = event.getItem().getAmount() - 1 == 0 ? new ItemStack(Material.AIR, 2) : event.getItem();
                    stack.setAmount(stack.getAmount() - 1);
                    event.getPlayer().getInventory().setItem(event.getHand(), stack);
                    event.getPlayer().setMaxHealth(event.getPlayer().getMaxHealth() + 2);
                }
            }
        }
    }

}
