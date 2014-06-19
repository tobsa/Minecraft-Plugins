package wirelessredstone;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class Device {
    private Location location;
    private String channel;

    public Device(Location location, String channel) {
        this.setLocation(location);
        this.setChannel(channel);
    }

    public Location getLocation() {
        return location;
    }

    public final void setLocation(Location location) {
        this.location = location;
    }

    public Block getBlock() {
        return location.getBlock();
    }

    public String getChannel() {
        return channel;
    }

    public final void setChannel(String channel) {
        this.channel = channel;
    }
}
