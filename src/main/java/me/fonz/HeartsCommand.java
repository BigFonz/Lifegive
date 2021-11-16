package me.fonz;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HeartsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            Player target = Bukkit.getPlayer(args[0]);

            if (args.length > 1) {
                try {
                    int health = Integer.parseInt(args[1]) * 2;

                    if (health < 2) {
                        sender.sendMessage(ChatColor.RED + "The hearts cannot be below 1!");
                        return true;
                    }

                    target.setMaxHealth(health);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Set " + target.getName() + "'s hearts to &6&l" + health / 2));
                } catch (NumberFormatException e) {
                    sender.sendMessage(ChatColor.RED + "Invalid Integer!");
                }

                return true;
            }

            if (target != null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7" + target.getName() + " has &6&l" + target.getMaxHealth() / 2 + " &r&7hearts"));
            } else {
                sender.sendMessage(ChatColor.RED + "That player is not online!");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You need to provide a player! /hearts <player>");
        }

        return true;
    }

}
