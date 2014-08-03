package soundblocks;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListExecutor implements CommandExecutor {

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
            player.sendMessage(PlayerMessage.invalidNumber1(page));
            return true;
        }
        
        if(page != 1 && page != 2) {
            player.sendMessage(PlayerMessage.invalidNumber2(page));
            return true;
        }
        
        player.sendMessage(PlayerMessage.listFooter(page));
        
        int begin = page * 98 - 98;
        int end   = begin + 98;
        if(end > Sound.values().length)
            end = Sound.values().length;
        
        for(int i = begin; i < end; i++)
            player.sendMessage((i + 1) + ". " + Sound.values()[i]);
        
        player.sendMessage(PlayerMessage.listFooter(page));
                
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
