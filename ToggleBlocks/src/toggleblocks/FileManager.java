package toggleblocks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class FileManager {
    
    public static RegionManager load() {
        SerializedRegionManager serializedRegionManager = load("plugins/ToggleBlocks/regions");
        
        if(serializedRegionManager == null)
            return new RegionManager(new LinkedHashMap());
        
        return serializedRegionManager.getRegionManager();
    }
    
    public static void save(RegionManager regionManager) {
        if(!isDirectory("plugins/ToggleBlocks"))
            makeDirectory("plugins/ToggleBlocks");
        
        save(new SerializedRegionManager(regionManager), "plugins/ToggleBlocks/regions");
    }
        
    private static void save(SerializedRegionManager regionManager, String name) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name));
            oos.writeObject(regionManager);
            oos.close();            
        } catch (IOException ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + ex.toString());
        }
    }
    
    private static SerializedRegionManager load(String name) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name));
            SerializedRegionManager regionManager = (SerializedRegionManager)ois.readObject();
            ois.close();
            return regionManager;
        } catch (IOException | ClassNotFoundException ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + ex.toString());
            return null;
        }
    }
    
    private static boolean isDirectory(String directory) {
        return new File(directory).isDirectory();
    }
    
    private static boolean makeDirectory(String directory) {
        return new File(directory).mkdir();
    }
}
