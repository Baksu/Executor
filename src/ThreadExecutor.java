import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by Baksu on 2015-08-18.
 */
public class ThreadExecutor implements Runnable  {

    Logger logger = Logger.getLogger("com.baksu");
    private String executorId;
    private Thread t;
    private CommandExecutor commandExecutor;

    ThreadExecutor(CommandExecutor commandExecutor, String executorId){
        this.executorId = executorId;
        this.commandExecutor = commandExecutor;
        logger.info("Create player " + executorId);
    }

    public void start(){
        logger.info("Start player " + executorId);
        if (t == null) {
            t = new Thread (this, executorId);
            t.start ();
        }
    }

    @Override
    public void run() {
        Command command;
        int randomTime;
        Random r = new Random();

        while(true){
            randomTime = r.nextInt(1500 - 500) + 500;
            try {
                Thread.sleep(randomTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if((command = commandExecutor.getTask())!= null) {
                logger.info(executorId + " get command " + command.getPlayerId());
                randomTime = r.nextInt(2000 - 500) + 500;
                try {
                    Thread.sleep(randomTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                command.execute();
                logger.info(executorId + " execute command " + command.getTimestamp() + " " + command.getPlayerId());
                commandExecutor.releasePlayerCommand(command.getPlayerId());
                logger.info(executorId + " release player " + command.getPlayerId());
            }
        }
    }
}
