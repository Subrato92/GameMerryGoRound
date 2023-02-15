import entities.Game;
import entities.Player;
import exceptions.AllSeatsOccupiedException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        System.out.println("MerryGo Round Game !");

        int availablePlayers = 10;
        List<Player> playerPool = new ArrayList<>();

        for(int i=1; i<=availablePlayers; i++){
            playerPool.add(new Player());
        }

        int totalSeats = 5;
        Game game = new Game(totalSeats);


        // Add Players to the Game
        for(int i=1; i<=totalSeats; i++){
            try {
                game.addPlayer(playerPool.get(i-1));
            }catch(AllSeatsOccupiedException ex){
                System.out.println("Error In Adding Players : "+ ex.getMessage());
            }
        }

        List<PullHandleTask> taskList = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        taskList.add(new PullHandleTask(playerPool.get(0),3));
        taskList.add(new PullHandleTask(playerPool.get(0),1));
        taskList.add(new PullHandleTask(playerPool.get(0),2));
        taskList.add(new PullHandleTask(playerPool.get(0),1));
        taskList.add(new PullHandleTask(playerPool.get(3),4));
        taskList.add(new PullHandleTask(playerPool.get(0),1));

        for(int i=0; i<taskList.size(); i++){
            //executor.submit(taskList.get(i));
            taskList.get(i).run();
        }

        executor.shutdown();

        if(executor.isTerminated()){
            System.out.println("All Task Executed");
        }


    }
}