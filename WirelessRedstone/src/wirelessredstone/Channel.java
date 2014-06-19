package wirelessredstone;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.bukkit.Location;

public class Channel {
    private WirelessRedstone plugin;
    private List<Transmitter> transmitters = new ArrayList();
    private List<Receiver> receivers = new ArrayList();

    public Channel(WirelessRedstone plugin) {
        this.plugin = plugin;

        // Load the transmitters
        if (plugin.getConfig().contains("transmitters")) {
            for (String channel : plugin.getConfig().getConfigurationSection("transmitters").getKeys(false)) {
                double x = plugin.getConfig().getDouble("transmitters." + channel + ".x");
                double y = plugin.getConfig().getDouble("transmitters." + channel + ".y");
                double z = plugin.getConfig().getDouble("transmitters." + channel + ".z");

                transmitters.add(new Transmitter(new Location(plugin.getServer().getWorld("world"), x, y, z), channel));
            }
        }

        // Load the receivers
        if (plugin.getConfig().contains("receivers")) {
            for (String channel : plugin.getConfig().getConfigurationSection("receivers").getKeys(false)) {
                int nbReceivers = 1;
                for (String index : plugin.getConfig().getConfigurationSection("receivers." + channel).getKeys(false)) {
                    double x = plugin.getConfig().getDouble("receivers." + channel + "." + index + ".x");
                    double y = plugin.getConfig().getDouble("receivers." + channel + "." + index + ".y");
                    double z = plugin.getConfig().getDouble("receivers." + channel + "." + index + ".z");

                    receivers.add(new Receiver(new Location(plugin.getServer().getWorld("world"), x, y, z), channel, nbReceivers++));
                }
            }
        }

        // Bind the receivers to the transmitters
        for (Transmitter transmitter : transmitters) {
            for (Receiver receiver : receivers) {
                if (transmitter.hasSameChannel(receiver))
                    transmitter.addReceiver(receiver);
            }
        }
    }

    public boolean createTransmitter(Location location, String channel) {
        for (Transmitter transmitter : transmitters) {
            if (transmitter.getChannel().equals(channel))
                return false;
        }

        // Save the transmitter to the config file
        plugin.getConfig().set("transmitters." + channel + ".x", location.getX());
        plugin.getConfig().set("transmitters." + channel + ".y", location.getY());
        plugin.getConfig().set("transmitters." + channel + ".z", location.getZ());
        plugin.saveConfig();

        Transmitter transmitter = new Transmitter(location, channel);

        Collection<Receiver> remove = new ArrayList();

        // Add existing receivers to the transmitter if they have the same channel
        for (Receiver receiver : receivers) {
            if (transmitter.hasSameChannel(receiver)) {
                transmitter.addReceiver(receiver);
                remove.add(receiver);
            }
        }

        transmitters.add(transmitter);
        receivers.removeAll(remove);

        return true;
    }

    public void createReceiver(Location location, String channel) {
        // Calculate the amount of receivers on the same channel
        int index = 1;
        if (plugin.getConfig().contains("receivers." + channel))
            index = plugin.getConfig().getConfigurationSection("receivers." + channel).getKeys(false).size() + 1;

        Receiver receiver = new Receiver(location, channel, index);

        // Save the receiver to the config file
        plugin.getConfig().set("receivers." + channel + ".receiver" + receiver.getIndex() + ".x", location.getX());
        plugin.getConfig().set("receivers." + channel + ".receiver" + receiver.getIndex() + ".y", location.getY());
        plugin.getConfig().set("receivers." + channel + ".receiver" + receiver.getIndex() + ".z", location.getZ());
        plugin.saveConfig();

        // Check if the receiver has the same channel as a transmitter
        for (Transmitter transmitter : transmitters) {
            if (transmitter.hasSameChannel(receiver)) {
                transmitter.addReceiver(receiver);
                return;
            }
        }

        receivers.add(receiver);
    }

    public List<Transmitter> getTransmitters() {
        return transmitters;
    }

    public List<Receiver> getReceivers() {
        return receivers;
    }
}
