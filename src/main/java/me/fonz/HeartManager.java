package me.fonz;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class HeartManager {

    private NamespacedKey key;
    private ItemStack heart;
    private ShapedRecipe heartRecipe;

    public HeartManager(Lifegive plugin) {
        key = new NamespacedKey(plugin, "lifegive_heart");
        heart = new ItemStack(Material.RED_DYE);
        ItemMeta heartMeta = heart.getItemMeta();
        heartMeta.setDisplayName(ChatColor.RED + "\u2764 " + ChatColor.WHITE + "Heart");
        heart.setItemMeta(heartMeta);

        heartRecipe = new ShapedRecipe(key, heart);
        heartRecipe.shape("ODO", "D D", "ODO");
        heartRecipe.setIngredient('O', Material.OBSIDIAN);
        heartRecipe.setIngredient('D', Material.DIAMOND_BLOCK);
        plugin.getServer().addRecipe(heartRecipe);
    }

    public ItemStack getHeart() {
        return heart;
    }
}
