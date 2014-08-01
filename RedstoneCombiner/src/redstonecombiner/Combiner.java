package redstonecombiner;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class Combiner {
    private String playerName;
    private String name;
    private List<Block> links = new ArrayList();
    private Block toggleBlock;
    
    public Combiner(String playerName, String name, Block toggleBlock) {
        this.playerName = playerName;
        this.name = name;
        this.toggleBlock = toggleBlock;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public String getName() {
        return name;
    }
    
    public void addLink(Block link) {
        links.add(link);
    }
    
    public boolean removeLink(Block link) {        
        return links.remove(link);
    }
    
    public List<Block> getLinks() {
        return links;
    }
    
    public Block getToggleBlock() {
        return toggleBlock;
    }
    
    public void update() {           
        if(isPowered())
            toggleBlock.setType(Material.REDSTONE_TORCH_ON);
        else 
            toggleBlock.setType(Material.SIGN_POST);
    }
    
    public boolean isPowered() {
        if(links.isEmpty())
            return false;
        
        for(Block link : links) {
            if(!(link.isBlockPowered() || link.isBlockIndirectlyPowered()))
                return false;
        }        
        
        return true;
    }
}
