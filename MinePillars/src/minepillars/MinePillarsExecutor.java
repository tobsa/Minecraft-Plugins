package minepillars;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MinePillarsExecutor implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {        
        if (!(sender instanceof Player))
            return true;

        Player player = (Player)sender;
        
        if(args.length != 1) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
        
        Integer length = getInteger(args[0]);
        if(length == null) {
            player.sendMessage(PlayerMessage.invalidNumber(args[0]));
            return true;
        }
        
        int nbPillars = length / 2 + 1;
        
        List<Integer> numbers = new ArrayList();
        for (int i = 2; i <= nbPillars; i++) {
            float spacing = (float) (length - i) / (float) (i - 1);

            if ((int) spacing != spacing) {
                continue;
            }
            
            numbers.add((int)spacing);
        }
        
        player.sendMessage(PlayerMessage.numbers(length, numbers));
        return true;
    }
    
    private Integer getInteger(String argument) {
        try {
            return Integer.valueOf(argument);
        } catch(NumberFormatException ex) {
            return null;
        }
    }
}
