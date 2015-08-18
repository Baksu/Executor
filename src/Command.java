import java.sql.Timestamp;

/**
 * Created by Baksu on 2015-08-17.
 */
public class Command implements ICommand {
    private final Timestamp timestamp;
    private final String playerId;

    public Command(Timestamp timestamp, String playerId) {
        this.timestamp = timestamp;
        this.playerId = playerId;
    }

    public String getPlayerId(){
        return playerId;
    }

    public Timestamp getTimestamp(){
        return timestamp;
    }

    @Override
    public void execute() {
        System.out.println("Command " + playerId + " was execute");
    }
}
