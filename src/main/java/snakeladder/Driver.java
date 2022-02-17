package snakeladder;

import snakeladder.models.Ladder;
import snakeladder.models.Player;
import snakeladder.models.Snake;
import snakeladder.service.SnakeAndLadderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int noOfSnakes = sc.nextInt();
        List<Snake> snakes = new ArrayList<Snake>();
        for(int i=0;i<noOfSnakes;i++){
            snakes.add(new Snake(sc.nextInt(), sc.nextInt()));
        }

        int noOfLadders = sc.nextInt();
        List<Ladder> ladders = new ArrayList<Ladder>();
        for(int i=0;i<noOfLadders;i++){
            ladders.add(new Ladder(sc.nextInt(), sc.nextInt()));
        }

        int noOfPlayers = sc.nextInt();
        List<Player> players = new ArrayList<Player>();
        for(int i=0;i<noOfPlayers;i++){
            players.add(new Player(sc.next()));
        }

//        for(Snake snake:snakes){
//            System.out.println("Snake: "+snake.getStart()+", "+snake.getEnd());
//        }
//
//        for(Ladder ladder:ladders){
//            System.out.println("ladder: "+ladder.getStart()+", "+ladder.getEnd());
//        }
//
//        for(Player player : players){
//            System.out.println("player: "+player.getName()+", "+player.getId());
//        }


        SnakeAndLadderService snakeAndLadderService = new SnakeAndLadderService();
        snakeAndLadderService.setLadders(ladders);
        snakeAndLadderService.setSnakes(snakes);
        snakeAndLadderService.setPlayers(players);

        snakeAndLadderService.startGame();
    }
}
