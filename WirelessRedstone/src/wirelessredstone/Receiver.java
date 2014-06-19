package wirelessredstone;

import org.bukkit.Location;

public class Receiver extends Device {

    private int index;

    public Receiver(Location location, String channel) {
        super(location, channel);
    }
    
    public Receiver(Location location, String channel, int index) {
        super(location, channel);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
