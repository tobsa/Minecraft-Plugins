package interactblocks.giveitem;

import interactblocks.PlayerMessage;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemListExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
        
        if(args.length != 1) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
        
        Integer page = getInteger(args[0]);
        if(page == null) {
            player.sendMessage(PlayerMessage.invalidNumber1(args[0]));
            return true;
        }
        
        int itemsPerPage = 80;
        
        int numPages = Material.values().length / itemsPerPage;
        if(Material.values().length % itemsPerPage > 0)
            numPages++;

        if(page < 1 || page > numPages) {
            player.sendMessage(PlayerMessage.invalidNumber2(page, numPages));
            return true;
        }
        
        player.sendMessage(PlayerMessage.listMaterial(page, numPages));
        
        int begin = page * itemsPerPage - itemsPerPage;
        int end   = begin + itemsPerPage;
        if(end > Material.values().length)
            end = Material.values().length;
        
        for(int i = begin; i < end; i++)
            player.sendMessage((i + 1) + ". " + Material.values()[i].toString());
                
        player.sendMessage(PlayerMessage.listMaterial(page, numPages));
                
        return true;
    }
    
    public Integer getInteger(String number) {
        try {
            return Integer.valueOf(number);
        } catch(NumberFormatException ex) {
            return null;
        }
    }
}
