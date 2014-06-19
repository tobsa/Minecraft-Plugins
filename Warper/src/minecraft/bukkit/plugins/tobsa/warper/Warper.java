package minecraft.bukkit.plugins.tobsa.warper;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Warper extends JavaPlugin implements Listener {
	
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "To few arguments.");
            return true;
        }

        if (command.getName().equalsIgnoreCase("warper")) {
            if (args[0].equalsIgnoreCase("help"))
                onHelpCommand(player, args);
            else if (args[0].equalsIgnoreCase("save"))
                onSaveCommand(player, args);
            else if (args[0].equalsIgnoreCase("load"))
                onLoadCommand(player, args);
            else if (args[0].equalsIgnoreCase("delete"))
                onDeleteCommand(player, args);
            else if (args[0].equalsIgnoreCase("clear"))
                onClearCommand(player, args);
            else if (args[0].equalsIgnoreCase("list"))
                onListCommand(player, args);
            else
                player.sendMessage(ChatColor.RED + "Unknown command.");
        }

        return true;
    }

    private void onHelpCommand(Player player, String[] args) {
        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Arguments are not correct.");
            return;
        }

        player.sendMessage(ChatColor.DARK_AQUA + "----- Warper -----");
        player.sendMessage("/wa help - Show a list of all available commands.");
        player.sendMessage("/wa save <name> - Save a position.");
        player.sendMessage("/wa load <name> - Load a position.");
        player.sendMessage("/wa delete <name> - Delete a position.");
        player.sendMessage("/wa clear - Clear all the positions.");
        player.sendMessage("/wa list - Show a list of all available positions.");
    }

    private void onSaveCommand(Player player, String[] args) {
        if (args.length != 2) {
            player.sendMessage(ChatColor.RED + "Arguments are not correct.");
            return;
        }

        getConfig().set(player.getName() + "." + args[1] + ".x", player.getLocation().getX());
        getConfig().set(player.getName() + "." + args[1] + ".y", player.getLocation().getY());
        getConfig().set(player.getName() + "." + args[1] + ".z", player.getLocation().getZ());
        getConfig().set(player.getName() + "." + args[1] + ".yaw", player.getLocation().getYaw());
        getConfig().set(player.getName() + "." + args[1] + ".pitch", player.getLocation().getPitch());
        saveConfig();

        player.sendMessage(ChatColor.AQUA + "Position '" + args[1] + "' saved.");
    }

    private void onLoadCommand(Player player, String[] args) {
        if (args.length != 2) {
            player.sendMessage(ChatColor.RED + "Arguments are not correct.");
            return;
        }

        if (!getConfig().contains(player.getName() + "." + args[1])) {
            player.sendMessage(ChatColor.RED + "Position '" + args[1] + "' doesn't exist.");
            return;
        }

        double x = getConfig().getDouble(player.getName() + "." + args[1] + ".x");
        double y = getConfig().getDouble(player.getName() + "." + args[1] + ".y");
        double z = getConfig().getDouble(player.getName() + "." + args[1] + ".z");
        float yaw = (float) getConfig().getDouble(player.getName() + "." + args[1] + ".yaw");
        float pitch = (float) getConfig().getDouble(player.getName() + "." + args[1] + ".pitch");

        player.teleport(new Location(player.getWorld(), x, y, z, yaw, pitch));
    }

    private void onDeleteCommand(Player player, String[] args) {
        if (args.length != 2) {
            player.sendMessage(ChatColor.RED + "Arguments are not correct.");
            return;
        }

        if (!getConfig().contains(player.getName() + "." + args[1])) {
            player.sendMessage(ChatColor.RED + "Position '" + args[1] + "' doesn't exist.");
            return;
        }

        getConfig().set(player.getName() + "." + args[1], null);
        saveConfig();

        player.sendMessage(ChatColor.AQUA + "Position '" + args[1] + "' deleted.");
    }

    private void onClearCommand(Player player, String[] args) {
        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Arguments are not correct.");
            return;
        }

        player.sendMessage(ChatColor.AQUA + "List has been cleared.");

        getConfig().set(player.getName(), null);
        saveConfig();
    }

    private void onListCommand(Player player, String[] args) {
        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Arguments are not correct.");
            return;
        }

        player.sendMessage(ChatColor.DARK_AQUA + "----- Warper -----");

        if (getConfig().contains(player.getName())) {
            for (String position : getConfig().getConfigurationSection(player.getName()).getKeys(false)) {
                player.sendMessage(position);
            }
        }
    }
}
