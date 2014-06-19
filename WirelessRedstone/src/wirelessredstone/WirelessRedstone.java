package wirelessredstone;

import org.bukkit.plugin.java.JavaPlugin;

public class WirelessRedstone extends JavaPlugin {
    Channel channel;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new OnSignChangeEvent(this), this);
        getServer().getPluginManager().registerEvents(new OnBlockRedstoneEvent(this), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreakEvent(this), this);

        channel = new Channel(this);
    }
    
    @Override
    public void onDisable() {

    }
}
