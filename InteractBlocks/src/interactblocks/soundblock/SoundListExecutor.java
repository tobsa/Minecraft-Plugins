package interactblocks.soundblock;

import interactblocks.PlayerMessage;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SoundListExecutor implements CommandExecutor {

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
        
        int soundsPerPage = 80;
        
        int numPages = Sound.values().length / soundsPerPage;
        if(Sound.values().length % soundsPerPage > 0)
            numPages++;
        
        if(page < 1 || page > numPages) {
            player.sendMessage(PlayerMessage.invalidNumber2(page, numPages));
            return true;
        }
        
        player.sendMessage(PlayerMessage.listSound(page, numPages));
        
        int begin = page * soundsPerPage - soundsPerPage;
        int end   = begin + soundsPerPage;
        if(end > Sound.values().length)
            end = Sound.values().length;
        
        for(int i = begin; i < end; i++)
            player.sendMessage((i + 1) + ". " + Sound.values()[i]);
        
        player.sendMessage(PlayerMessage.listSound(page, numPages));
                
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
