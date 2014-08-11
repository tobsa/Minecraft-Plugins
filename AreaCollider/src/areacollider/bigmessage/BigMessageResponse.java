package areacollider.bigmessage;

import areacollider.CollisionResponse;
import areacollider.SerializedCollisionResponse;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class BigMessageResponse implements CollisionResponse {
    private List<String> messages = new ArrayList(); 
    private String message;
    
    public BigMessageResponse() {
        message = ChatColor.YELLOW + " This is a sub-line!";
    }

    @Override
    public void onCollision(Player player) {
        player.sendMessage(ChatColor.GREEN + "========================================");
        player.sendMessage(ChatColor.GREEN + "==");
        player.sendMessage(ChatColor.GREEN + "==" + ChatColor.WHITE+ " Welcome to World 1 ");
        player.sendMessage(ChatColor.GREEN + "==");
        player.sendMessage(ChatColor.GREEN + "==" + message);
        player.sendMessage(ChatColor.GREEN + "========================================");
    }

    @Override
    public SerializedCollisionResponse getSerializedResponse() {
        return new SerializedBigMessageResponse("");
    }
}
