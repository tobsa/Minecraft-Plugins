package basepack;

public class BaseItem {
    private String name;
    private String playerName;
    
    public BaseItem(String name, String playerName) {
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
}
