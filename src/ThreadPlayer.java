import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by Baksu on 2015-08-18.
 */
public class ThreadPlayer implements Runnable {

    Logger logger = Logger.getLogger("com.baksu");

    private CommandExecutor commandExecutor;
    private String threadId;
    private Thread t;

    ThreadPlayer(CommandExecutor commandExecutor, String threadId) {
        this.commandExecutor = commandExecutor;
        this.threadId = threadId;
        logger.info("Create " + threadId);
    }

    public void start () {
        logger.info("Start " + threadId);
        if (t == null) {
            t = new Thread (this, threadId);
            t.start ();
        }
    }

    @Override
    public void run() {
        Command command;
        int randomTime;
        Date date= new Date();
        Random r = new Random();

        while(true){
            randomTime = r.nextInt(1000 - 200) + 200;
            try {
                Thread.sleep(randomTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            command = new Command(new Timestamp(date.getTime()),threadId);
            commandExecutor.queueCommand(command);
            logger.info(threadId + " send command");
        }
    }
}
