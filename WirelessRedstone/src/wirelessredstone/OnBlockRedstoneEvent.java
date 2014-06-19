package wirelessredstone;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class OnBlockRedstoneEvent implements Listener {
    private WirelessRedstone plugin;

    public OnBlockRedstoneEvent(WirelessRedstone plugin) {
        this.plugin = plugin;
        this.plugin.saveConfig();
    }

    @EventHandler
    public void onBlockRedstoneEvent(BlockRedstoneEvent event) {
        for (Transmitter transmitter : plugin.channel.getTransmitters()) {
            // If a transmitter is powered then transmit it to it's receivers
            if (transmitter.getBlock().isBlockPowered()) {
                for (Receiver receiver : transmitter.getReceivers()) {
                    receiver.getBlock().setType(Material.REDSTONE_TORCH_ON);
                }
            } else {
                for (Receiver receiver : transmitter.getReceivers()) {
                    receiver.getBlock().setType(Material.SIGN_POST);

                    if (receiver.getBlock().getState() instanceof Sign) {
                        Sign sign = (Sign) receiver.getBlock().getState();
                        sign.setLine(0, "[WRr]");
                        sign.setLine(1, receiver.getChannel());
                    }
                }
            }
        }
    }
}
