package puzzlepack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class BaseFileManager {
    
    public static void saveObject(Object object, String name) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name));
            oos.writeObject(object);
            oos.close();            
        } catch (IOException ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + ex.toString());
        }
    }
    
    public static Object loadObject(String name) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name));
            Object object = ois.readObject();
            ois.close();
            return object;
        } catch (IOException | ClassNotFoundException ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + ex.toString());
            return null;
        }
    }
    
    public static boolean isDirectory(String directory) {
        return new File(directory).isDirectory();
    }
    
    public static boolean makeDirectory(String directory) {
        return new File(directory).mkdir();
    }
    
}
