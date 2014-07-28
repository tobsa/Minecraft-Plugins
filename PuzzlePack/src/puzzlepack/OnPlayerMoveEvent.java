package puzzlepack;

import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.material.Wool;
import org.bukkit.util.Vector;

public class OnPlayerMoveEvent implements Listener {
    private PuzzlePack plugin;
    
    public OnPlayerMoveEvent(PuzzlePack plugin) {
        this.plugin = plugin;
    }
        
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Block block = event.getFrom().getBlock().getRelative(BlockFace.DOWN);
        Player player = event.getPlayer();
        
        handleElevatorBlock(player, event.getFrom().getBlock());
        handleSecretRooms(player, event);
        handleTeleportRooms(player, event);

        // Is the block the player walked upon a wool block?
        if (block.getType() == Material.WOOL) {
            Block below = block.getRelative(BlockFace.DOWN);
            Wool wool = new Wool(block.getType(), block.getData());

            handleBounceBlocks(player, wool, below);
            handleClearInventoryBlocks(player, wool);
        }
    }
    
    private void handleSecretRooms(Player player, PlayerMoveEvent event) {
        for(AreaSecretRoom area : plugin.getAreaManager().getSecretRooms())
            if(area.isInside(player, event.getTo().getBlock())) {
                player.sendMessage("Secret area found!");       
                player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);
            }
    }
    
    private void handleTeleportRooms(Player player, PlayerMoveEvent event) {
        for(AreaTeleportRoom area : plugin.getAreaManager().getTeleportRooms()) {
            if(area.isInside(player, event.getTo().getBlock())) {
                double x = area.getTeleport().getX();
                double y = area.getTeleport().getY();
                double z = area.getTeleport().getZ();
                
                Location location = new Location(player.getWorld(), x, y, z, area.getYaw(), area.getPitch());
                player.teleport(location);
                player.playSound(location, Sound.ENDERMAN_TELEPORT, 1, 1);
            }
        }
    }
    
    private void handleElevatorBlock(Player player, Block block) {
        if(checkSide(block, BlockFace.EAST) || checkSide(block, BlockFace.WEST) || checkSide(block, BlockFace.NORTH) || checkSide(block, BlockFace.SOUTH)) {
            player.setVelocity(new Vector(0, 0.25, 0));
            player.playSound(player.getLocation(), Sound.SHOOT_ARROW, 1, 1);
        }
    }
    
    private boolean checkSide(Block block, BlockFace face) {
        Block relative = block.getRelative(face);
        
        if(relative.getType() == Material.WOOL) {
            if(new Wool(relative.getType(), relative.getData()).getColor() == DyeColor.MAGENTA)
                return true;
        }
        
        return false;
    }
    
    private void handleBounceBlocks(Player player, Wool wool, Block below) {
        // If the wool is white then bounce the player
        if (wool.getColor() == DyeColor.WHITE) {
            player.setVelocity(new Vector(0, below.getTypeId() * 0.1 + 0.4, 0));
        }
    }
    
    private void handleClearInventoryBlocks(Player player, Wool wool) {
        // If the wool is pink then clear inventory
        if (wool.getColor() == DyeColor.PINK) {
            // Don't do this in creative mode...
            if (player.getGameMode() == GameMode.CREATIVE) {
                return;
            }

            PlayerInventory inv = player.getInventory();

            // Loop trough all inventory slots
            for (int i = 0; i < inv.getSize(); i++) {
                // If there's no item in a slot then continue to the next one
                if (inv.getItem(i) == null) {
                    continue;
                }

                // Clear all items except a few (down below)
                switch (inv.getItem(i).getType()) {
                    case GOLD_NUGGET:
                    case REDSTONE_WIRE:
                    case LEVER:
                    case LADDER:
                    case STONE_PLATE:
                    case WOOD_PLATE:
                    case STONE_BUTTON:
                    case WOOD_BUTTON:
                    case COOKED_BEEF:
                    case BREAD:
                    case COOKED_CHICKEN:
                    case COOKED_FISH:
                        break;
                    default:
                        inv.clear(i);
                }

            }
        }
    }

}
