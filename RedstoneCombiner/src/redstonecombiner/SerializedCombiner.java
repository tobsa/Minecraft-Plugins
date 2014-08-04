package redstonecombiner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.block.Block;

public class SerializedCombiner implements Serializable {    
    private String name;
    private String playerName;
    private SerializedBlock toggleBlock;
    private List<SerializedBlock> links = new ArrayList();
    
    public SerializedCombiner(Combiner combiner) {
        this.name = combiner.getName();
        this.playerName = combiner.getPlayerName();
        this.toggleBlock = new SerializedBlock(combiner.getToggleBlock());
        
        for(Block link : combiner.getLinks())
            links.add(new SerializedBlock(link));
            
    }
    
    public Combiner getCombiner() {
        Combiner combiner = new Combiner(playerName, name, toggleBlock.getBlock());
        
        for(SerializedBlock link : links) 
            combiner.addLink(link.getBlock());
        
        return combiner;
    }
}
