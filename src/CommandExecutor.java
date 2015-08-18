import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Baksu on 2015-08-17.
 */
public class CommandExecutor implements ICommandExecutor {
    ArrayList<String> player = new ArrayList<String>();
    LinkedList<Command> taskQueue = new LinkedList<Command>();

    @Override
    public void queueCommand(Command command) {
        synchronized (taskQueue){
            taskQueue.add(command);
        }
    }

    public Command getTask(){
        synchronized (taskQueue){
            synchronized (player) {
                if(!taskQueue.isEmpty()) {
                    Command presentComand;
                    for(int i = 0; i < taskQueue.size(); i++) {
                        presentComand = taskQueue.get(i);
                        if (player.isEmpty() || !player.contains(presentComand.getPlayerId())) {
                            taskQueue.remove(i);
                            player.add(presentComand.getPlayerId());
                            return presentComand;
                        }
                    }
                }
            }
            return null;
        }
    }

    public void releasePlayerCommand(String playerId){
        synchronized (player){
            player.remove(playerId);
        }
    }

}
