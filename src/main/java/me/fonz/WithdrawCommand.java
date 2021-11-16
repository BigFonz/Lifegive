package me.fonz;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WithdrawCommand implements CommandExecutor {
    private Lifegive plugin;

    public WithdrawCommand(Lifegive plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command is only available for players!");
            return true;
        }

        Player player = (Player) sender;
        if (player.getMaxHealth() - 2 <= 0) {
            player.sendMessage(ChatColor.RED + "You cannot withdraw your last heart!");
            return true;
        }
        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(ChatColor.RED + "Your inventory is full!");
            return true;
        }

        player.setMaxHealth(player.getMaxHealth() - 2);
        player.getInventory().addItem(plugin.getHeartManager().getHeart());
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You withdrew &6&l1 &r&7heart!"));
        return true;
    }

}
