/**
 * Created by Baksu on 2015-08-18.
 */
public class Main {

    private static final int playerAmount = 10;
    private static final int executorsAmount = 5;

    public static void main(String[] args) {
        CommandExecutor commandExecutor = new CommandExecutor();
        ThreadPlayer[] threadPlayers = new ThreadPlayer[playerAmount];
        ThreadExecutor[] threadExecutors = new ThreadExecutor[executorsAmount];

        for(int i = 0; i < playerAmount; i++){
            threadPlayers[i] = new ThreadPlayer(commandExecutor,"Player " + i );
            threadPlayers[i].start();
        }

        for(int i = 0; i < executorsAmount; i++){
            threadExecutors[i] = new ThreadExecutor(commandExecutor,"Executor " + i);
            threadExecutors[i].start();
        }
    }
}
