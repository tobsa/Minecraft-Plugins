package wirelessredstone;

import org.bukkit.ChatColor;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreakEvent implements Listener {
    private WirelessRedstone plugin;

    public OnBlockBreakEvent(WirelessRedstone plugin) {
        this.plugin = plugin;
        this.plugin.saveConfig();
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        for (Transmitter transmitter : plugin.channel.getTransmitters()) {
            // If a transmitter was destroyed
            if (transmitter.getBlock().equals(event.getBlock()) || transmitter.getBlock().getRelative(BlockFace.DOWN).equals(event.getBlock())) {
                // Erase the transmitter from the config file
                plugin.getConfig().set("transmitters." + transmitter.getChannel(), null);
                plugin.saveConfig();

                // Add the receivers to the unbound list
                for (Receiver receiver : transmitter.getReceivers())
                    plugin.channel.getReceivers().add(receiver);

                // Erase the transmitter from the list
                plugin.channel.getTransmitters().remove(transmitter);
                event.getPlayer().sendMessage(ChatColor.AQUA + "Transmitter (" + transmitter.getChannel() + ") was destroyed!");
                break;
            }

            for (Receiver receiver : transmitter.getReceivers()) {
                // If a receiver was destroyed
                if (receiver.getBlock().equals(event.getBlock()) || receiver.getBlock().getRelative(BlockFace.DOWN).equals(event.getBlock())) {
                    // Erase the receiver from the config file
                    plugin.getConfig().set("receivers." + receiver.getChannel() + ".receiver" + receiver.getIndex(), null);
                    plugin.saveConfig();

                    transmitter.getReceivers().remove(receiver);
                    event.getPlayer().sendMessage(ChatColor.AQUA + "Receiver (" + receiver.getChannel() + ") was destroyed!");
                    break;
                }
            }
        }

        // Check if an unbound receiver was destroyed
        for (Receiver receiver : plugin.channel.getReceivers()) {
            if (receiver.getBlock().equals(event.getBlock()) || receiver.getBlock().getRelative(BlockFace.DOWN).equals(event.getBlock())) {
                plugin.getConfig().set("receivers." + receiver.getChannel() + ".receiver" + receiver.getIndex(), null);
                plugin.saveConfig();

                plugin.channel.getReceivers().remove(receiver);
                event.getPlayer().sendMessage(ChatColor.AQUA + "Receiver (" + receiver.getChannel() + ") was destroyed!");
                break;
            }
        }
    }
}
