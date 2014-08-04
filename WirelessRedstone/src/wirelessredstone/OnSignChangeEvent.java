package wirelessredstone;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class OnSignChangeEvent implements Listener {
    private WirelessRedstone plugin;

    public OnSignChangeEvent(WirelessRedstone plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSignChangeEvent(SignChangeEvent event) {
        // If a transmitter was created
        if (event.getLine(0).equals("[WRt]")) {
            if (event.getLine(1).isEmpty()) {
                onSignEmpty(event);
                event.getPlayer().sendMessage(ChatColor.RED + "Channel must have a name!");
            } else if (plugin.channel.createTransmitter(event.getBlock().getLocation(), event.getLine(1))) {
                event.getPlayer().sendMessage(ChatColor.WHITE + "Transmitter (" + ChatColor.GREEN + event.getLine(1) + ChatColor.WHITE  + ") created!");
            } else {
                onSignEmpty(event);
                event.getPlayer().sendMessage(ChatColor.RED + "A transmitter with that channel already exist!");
            }
        } // If a receiver was created
        else if (event.getLine(0).equals("[WRr]")) {
            if (event.getLine(1).isEmpty()) {
                onSignEmpty(event);
                event.getPlayer().sendMessage(ChatColor.RED + "Channel must have a name!");
            } else {
                plugin.channel.createReceiver(event.getBlock().getLocation(), event.getLine(1));
                event.getPlayer().sendMessage(ChatColor.WHITE + "Receiver (" + ChatColor.GREEN + event.getLine(1) + ChatColor.WHITE + ") created!");
            }
        }
    }

    private void onSignEmpty(SignChangeEvent event) {
        event.setLine(0, "");
        event.setLine(1, "");
        event.setLine(2, "");
        event.setLine(3, "");
    }
}
