package snakeladder.service;

import snakeladder.models.Board;
import snakeladder.models.Ladder;
import snakeladder.models.Player;
import snakeladder.models.Snake;

import java.util.*;

public class SnakeAndLadderService {
    private Board board;
    private int initialPlayers;
    private Queue<Player> players;

    private static final int BOARD_SIZE = 100;

    public SnakeAndLadderService(int size){
        this.board = new Board(size);
        this.players = new LinkedList<Player>();
    }

    public SnakeAndLadderService(){
        this(SnakeAndLadderService.BOARD_SIZE);
    }

    public void setSnakes(List<Snake> snakes){
        board.setSnakes(snakes);
    }

    public void setLadders(List<Ladder> ladders){
        board.setLadders(ladders);
    }

    public void setPlayers(List<Player> players){
        this.players = new LinkedList<Player>();
        this.initialPlayers = players.size();
        Map<String, Integer> playersWithPosition = new HashMap<String, Integer>();
        for(Player player:players){
            this.players.add(player);
            playersWithPosition.put(player.getId(), 0);
        }
        board.setPlayerWithPosition(playersWithPosition);
    }

    private boolean hasPlayerWon(Player player){
        int playerPosition = board.getPlayerWithPosition().get(player.getId());
        int winningPosition = board.getSize();
        return playerPosition == winningPosition;
    }

    private boolean isGameCompleted(){
        int currentPlayers = players.size();
        return currentPlayers < initialPlayers;
    }

    private int getDiceRoll(){
        return DiceService.diceRoll();
    }

    public int checkNextPosition(int newPosition){
        int oldPosition;
        do{
            oldPosition = newPosition;
            for(Snake snake : board.getSnakes()){
                if(snake.getStart() == newPosition){
                    newPosition = snake.getEnd();
                }
            }

            for(Ladder ladder : board.getLadders()){
                if(ladder.getStart() == newPosition){
                    newPosition = ladder.getEnd();
                }
            }
        }while(oldPosition!=newPosition);
        return newPosition;
    }

    public void movePlayer(Player currentPlayer, int diceValue){
        int currentPosition = board.getPlayerWithPosition().get(currentPlayer.getId());
        int newPosition = currentPosition+diceValue;
        int boardSize = board.getSize();

        if(newPosition>boardSize){
            newPosition = currentPosition;
        }
        else{
            newPosition = checkNextPosition(newPosition);
        }
        board.getPlayerWithPosition().put(currentPlayer.getId(), newPosition);
        System.out.println(currentPlayer.getName() + " rolled "+diceValue + " and move from "+currentPosition+" to "+newPosition);
    }

    public void startGame(){
        while(!isGameCompleted()){
            int diceValue = getDiceRoll();
            Player currentPlayer = players.poll();
            System.out.println(diceValue + " of player: "+currentPlayer.getName());
            movePlayer(currentPlayer, diceValue);
            if(hasPlayerWon(currentPlayer)){
                System.out.println(currentPlayer.getName() + " has won the game.");
                board.getPlayerWithPosition().remove(currentPlayer.getName());
            }
            else{
                players.add(currentPlayer);
            }
        }
    }

}
