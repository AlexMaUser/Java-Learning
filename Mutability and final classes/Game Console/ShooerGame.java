import java.util.LinkedHashMap;
import java.util.Map;

public class ShooerGame extends Game<Shooter> {
    public ShooerGame(String gameName) {
        super(gameName);
    }

    @Override
    public Shooter createNewPlayer(String name) {
        return new Shooter(name);
    }

    @Override
    public Map<Character, GameAction> getGameActions(int playerIndex) {
        var map = new LinkedHashMap<>(Map.of(
                'F', new GameAction('F', "Find Prize", i -> this.findPrize(i)),
                'S', new GameAction('S', "Shooting use weapon", i -> this.useWeapon(i))
        ));
        map.putAll(getStandardAction());
        return map;
    }

    public boolean findPrize(int playerIndex) {
        return getPlayer(playerIndex).findPrize();
    }

    public boolean useWeapon(int playerIndex) {
        return getPlayer(playerIndex).useWeapon("pistol");
    }
}
