package minepillars;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MinePillars extends JavaPlugin implements Listener {
    private Map<Player, Integer> lengths = new HashMap();
    
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        lengths.put(event.getPlayer(), 0);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;
        
        if(args.length == 0) {
            onCommandHelp(player);
            return true;
        }
        
        if (command.getName().equalsIgnoreCase("MinePillars")) {
            if (args.length > 1) {
                player.sendMessage(ChatColor.RED + "Invalid amount of arguments!");
                return true;
            }

            if (args[0].equalsIgnoreCase("help"))
                onCommandHelp(player);
            else if (args[0].equalsIgnoreCase("add"))
                onCommandAdd(player);
            else if (args[0].equalsIgnoreCase("sub"))
                onCommandSub(player);
            else
                onCommandMinePillars(player, args);
        }

        return true;
    }

    private void onCommandHelp(Player player) {
        player.sendMessage(ChatColor.BLUE + "----- MinePillars -----");
        player.sendMessage("/mp help - Show a list of all available commands!");
        player.sendMessage("/mp <length> - Set the length to be calculated!");
        player.sendMessage("/mp add - Adds one to the length!");
        player.sendMessage("/mp sub - Subtracts one from the length!");
    }
    
    private void onCommandAdd(Player player) {
        lengths.put(player, lengths.get(player) + 1);
        showSpacings(player);
    }
    
    private void onCommandSub(Player player) {
        lengths.put(player, lengths.get(player) - 1);
        showSpacings(player);
    }
    
     private void onCommandMinePillars(Player player, String args[]) {
        try {
            lengths.put(player, Integer.parseInt(args[0]));
        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "Input is not a number!");
        }

        showSpacings(player);
    }
    
    private void showSpacings(Player player) {
        // Get the line width and the amount of pillars
        int lineWidth = lengths.get(player);
        int nbPillars = lineWidth / 2 + 1;

        String numbers = "Line (" + lineWidth + ") = [";

        // Calculate the spacing and add them to the list
        for (int i = 2; i <= nbPillars; i++) {
            float spacing = (float) (lineWidth - i) / (float) (i - 1);

            if ((int) spacing != spacing) {
                continue;
            }

            numbers += (int) spacing + ", ";
        }

        numbers = numbers.substring(0, numbers.length() - 2);
        numbers += "]";

        player.sendMessage(ChatColor.AQUA + numbers);
    }
}
