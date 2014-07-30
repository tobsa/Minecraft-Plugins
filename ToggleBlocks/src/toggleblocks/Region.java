package toggleblocks;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class Region {
    private ToggleBlocks plugin;
    private String playerName;
    private String name;
    private List<ToggleBlock> toggleBlocks = new ArrayList();
    private LinkBlock linkBlock;   
    
    public Region(ToggleBlocks plugin, String playerName, String name) {
        this.playerName = playerName;
        this.name = name;
        this.plugin = plugin;
    }
        
    public String getPlayerName() {
        return playerName;
    }
    
    public String getName() {
        return name;
    }
    
    public void addToggleBlock(ToggleBlock toggleBlock) {
        toggleBlocks.add(toggleBlock);
        
        int x = toggleBlock.getBlock().getX();
        int y = toggleBlock.getBlock().getY();
        int z = toggleBlock.getBlock().getZ();
        
        plugin.getConfig().set("toggleblocks." + name + ".blocks." + x + "," + y + "," + z + ".x", x);
        plugin.getConfig().set("toggleblocks." + name + ".blocks." + x + "," + y + "," + z + ".y", y);
        plugin.getConfig().set("toggleblocks." + name + ".blocks." + x + "," + y + "," + z + ".z", z);
        plugin.getConfig().set("toggleblocks." + name + ".blocks." + x + "," + y + "," + z + ".material", toggleBlock.getMaterial().toString());
        plugin.saveConfig();
    }
    
    public boolean removeBlock(Block block) {
        for(ToggleBlock toggleBlock : toggleBlocks) {
            if(block.equals(toggleBlock.getBlock())) {
                toggleBlocks.remove(toggleBlock);
                
                int x = toggleBlock.getBlock().getX();
                int y = toggleBlock.getBlock().getY();
                int z = toggleBlock.getBlock().getZ();
                plugin.getConfig().set("toggleblocks." + name + ".blocks." + x + "," + y + "," + z, null);
                plugin.saveConfig();
                return true;
            }
        }
        
        return false;
    }
    
    public void setLinkBlock(LinkBlock linkBlock) {
        this.linkBlock = linkBlock;
    }
        
    public LinkBlock getLinkBlock() {
        return linkBlock;
    }
        
    public boolean isLinkBlock(Block block) {
        if(linkBlock == null)
            return false;
        
        return linkBlock.getBlock().equals(block);
    }
            
    public void toggle() {
        for(ToggleBlock block : toggleBlocks)
            block.toggle();
    }
    
    public void toggleOn() {        
        for(ToggleBlock block : toggleBlocks)
            block.toggleOn();
    }
    
    public void toggleOff() {
        for(ToggleBlock block : toggleBlocks) {
            if(block.getMaterial() == Material.LADDER) {
                block.toggleOff();
            }
        }
        
        for(ToggleBlock block : toggleBlocks)
            block.toggleOff();
    }
}
