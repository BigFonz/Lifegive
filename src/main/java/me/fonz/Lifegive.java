package me.fonz;

import org.bukkit.plugin.java.JavaPlugin;

public final class Lifegive extends JavaPlugin {
    private HeartManager heartManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        heartManager = new HeartManager(this);

        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        getCommand("hearts").setExecutor(new HeartsCommand());
        getCommand("withdraw").setExecutor(new WithdrawCommand(this));
    }

    public HeartManager getHeartManager() {
        return heartManager;
    }
}
