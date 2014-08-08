package basepack;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ItemManager<Item extends BaseItem> {
    private Map<String, Item> items = new LinkedHashMap();
    
    public boolean add(Item item) {
        if(items.containsKey(item.getName()))
            return false;
        
        items.put(item.getName(), item);
        return true;
    }
    
    public List<Item> get() {
        return new ArrayList(items.values());
    }
    
    public Item get(String name) {
        return items.get(name);                
    }
    
    public Item get(String name, String playerName) {
        Item item = items.get(name);
        if(item == null)
            return null;
        
        if(item.getPlayerName().equalsIgnoreCase(playerName))
            return item;
        
        return null;
    }
    
    public void remove(String name) {
        items.remove(name);
    }
        
    public void setIndex(Item item, int index) {        
        List<Item> list = get();
        
        list.remove(item);
        list.add(index, item);
        
        items.clear();
        
        for(Item listitem : list)
            items.put(listitem.getName(), listitem);
    }
    
    public void renameItem(Item item, String newName) {        
        item.setName(newName);
        Map<String, Item> renamedItems = new LinkedHashMap();
        
        for(Item oldItem : items.values())
            renamedItems.put(oldItem.getName(), oldItem);
        
        items = renamedItems;
    }
} 
