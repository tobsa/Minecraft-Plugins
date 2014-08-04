package redstonecombiner;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class SerializedCombinerManager implements Serializable {
    private Map<String, SerializedCombiner> combiners = new LinkedHashMap();
    
    public SerializedCombinerManager(CombinerManager combinerManager) {
        for(Combiner combiner : combinerManager.getCombiners())
            combiners.put(combiner.getName(), new SerializedCombiner(combiner));
    }
    
    public CombinerManager getCombinerManager() {
        CombinerManager combinerManager = new CombinerManager();
        
        for(SerializedCombiner combiner : combiners.values())
            combinerManager.addCombiner(combiner.getCombiner());
        
        return combinerManager;
    }
}
