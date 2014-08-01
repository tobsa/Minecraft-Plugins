package telepads;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.material.Wool;

public class TelepadToExecutor implements CommandExecutor {
    private TelepadManager telepadManager;

    public TelepadToExecutor(TelepadManager telepadManager) {
        this.telepadManager = telepadManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        
        if(args.length != 1) {
            player.sendMessage(PlayerMessage.getInvalidArguments(command.getUsage()));
            return true;
        }
        
        Telepad telepad = telepadManager.getTelepad(player.getPlayerListName(), args[0]);
        if(telepad == null) {
            player.sendMessage(PlayerMessage.getMissingTelepad(args[0]));
            return true;
        }
                
        float yaw = player.getLocation().getYaw();
        float pitch = player.getLocation().getPitch();
        
        telepad.setTo(player.getLocation(), yaw, pitch);
        telepadManager.saveTo(telepad);
        player.sendMessage(PlayerMessage.getTelepadToUpdated(args[0]));

        return true;
    }

}
