package basepack;

import java.util.ArrayList;
import java.util.List;

public class BaseGroup<Item extends BaseItem> {
    private String name;
    private String playerName;
    private List<Item> items = new ArrayList();
    
    public BaseGroup(String name, String playerName) {
        this.name = name;
        this.playerName = playerName;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void add(Item item) {
        items.add(item);
    }
    
    public void remove(Item item) {        
        items.remove(item);
    }
    
    public List<Item> get() {
        return items;
    }
    
    public boolean contains(Item item) {
        return items.contains(item);
    }
    
    public void rename(String originalName, String newName) {
        for(Item area : items)
            if(area.getName().equals(originalName))
                area.setName(newName);
    }
}
