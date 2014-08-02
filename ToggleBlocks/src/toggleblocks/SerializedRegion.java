package toggleblocks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;

public class SerializedRegion implements Serializable {
    private String name;
    private String playerName;
    private SerializedBlock linkBlock;
    private LinkType linkType;
    private List<SerializedBlock> serializedBlocks = new ArrayList();
    
    public SerializedRegion(Region region) {
        this.name = region.getName();
        this.playerName = region.getPlayerName();
        
        if(region.getLinkBlock() != null) {
            this.linkBlock = new SerializedBlock(region.getLinkBlock().getBlock());
            this.linkType = region.getLinkBlock().getLinkType();
        }
        
        for(ToggleBlock toggleBlock : region.getToggleBlocks())
            serializedBlocks.add(new SerializedBlock(toggleBlock));
    }
        
    public Region getRegion() {
        Region region = new Region(name, playerName);
            
        if(linkBlock != null) {
            int x = linkBlock.getX();
            int y = linkBlock.getY();
            int z = linkBlock.getZ();

            region.setLinkBlock(new LinkBlock(Bukkit.getWorld("world").getBlockAt(x, y, z), linkType));
        }

        for(SerializedBlock serializedBlock : serializedBlocks) {
            int x = serializedBlock.getX();
            int y = serializedBlock.getY();
            int z = serializedBlock.getZ();
            String material = serializedBlock.getMaterial();
                        
            region.addBlock(new ToggleBlock(Bukkit.getWorld("world").getBlockAt(x, y, z), Material.valueOf(material)));
        }
        
        return region;
    }
}
