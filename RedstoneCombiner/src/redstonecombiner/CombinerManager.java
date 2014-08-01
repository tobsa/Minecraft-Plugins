package redstonecombiner;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.block.Block;

public class CombinerManager {
    private Map<String, Combiner> combiners = new LinkedHashMap();
    
    public CombinerManager() {
        combiners = FileManager.load();
    }
    
    public void addCombiner(Combiner combiner) {
        combiners.put(combiner.getName(), combiner);
        FileManager.saveCombiner(combiner);        
    }
    
    public Combiner getCombiner(String playerName, String name) {
        Combiner combiner = combiners.get(name);
        if(combiner == null)
            return null;
        
        if(combiner.getPlayerName().equalsIgnoreCase(playerName))
            return combiner;
        
        return null;
    }
    
    public void renameCombiner(Combiner combiner, String newName) {
        Combiner newCombiner = new Combiner(combiner.getPlayerName(), newName, combiner.getToggleBlock());
        for(Block link : combiner.getLinks()) {
            newCombiner.addLink(link);
            FileManager.saveLink(newName, link);
        }
        
        removeCombiner(combiner);
        addCombiner(newCombiner);
    }
    
    public List<Combiner> getCombiners() {
        return new ArrayList(combiners.values());
    }

    public void removeCombiner(Combiner combiner) {
        combiners.remove(combiner.getName());
        FileManager.removeCombiner(combiner);
    }
}
