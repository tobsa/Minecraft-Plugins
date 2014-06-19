package wirelessredstone;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;

public class Transmitter extends Device {
    private List<Receiver> receivers = new ArrayList<Receiver>();

    public Transmitter(Location location, String channel) {
        super(location, channel);
    }

    public List<Receiver> getReceivers() {
        return receivers;
    }

    public void addReceiver(Receiver receiver) {
        receivers.add(receiver);
    }

    public void removeReceiver(Receiver receiver) {
        receivers.remove(receiver);
    }

    public boolean hasSameChannel(Receiver receiver) {
        return getChannel().equals(receiver.getChannel());
    }
}
