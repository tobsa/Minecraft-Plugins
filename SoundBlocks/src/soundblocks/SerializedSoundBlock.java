package soundblocks;

import java.io.Serializable;
import org.bukkit.Bukkit;
import org.bukkit.Sound;

public class SerializedSoundBlock implements Serializable {
    private int x;
    private int y;
    private int z;
    private String sound;
    private String world;
    
    public SerializedSoundBlock(SoundBlock soundBlock) {
        this.x = soundBlock.getBlock().getX();
        this.y = soundBlock.getBlock().getY();
        this.z = soundBlock.getBlock().getZ();
        this.sound = soundBlock.getSound().toString();
        this.world = soundBlock.getBlock().getWorld().getName();
    }
    
    public SoundBlock getSoundBlock() {
        return new SoundBlock(Bukkit.getWorld(world).getBlockAt(x, y, z), Sound.valueOf(sound));
    }
}
