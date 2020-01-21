package jeu;

import cell.*;
import ui.*;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players = new ArrayList<>();
    private Grid grid = new Grid();
    private Window window;

    public Game() {

        Player player1 = new Player("Player 1", "Green", 1, new int[]{0, 1, 0, 1});
        Player player2 = new Player("Player 2", "Red", 2, new int[]{0, 5, 0, 5});
        players.add(player1);
        players.add(player2);
        grid.initGrid(player1, player2);
    }

    public Game(int nbPlayer) {
        Grid grid = new Grid();
        Player player1;
        Player player2;
        Player player3;
        Player player4;
        switch (nbPlayer) {
            case 2:
                player1 = new Player("Player 1", "Green", 1, new int[]{0, 1, 0, 1});
                player2 = new Player("Player 2", "Red", 2, new int[]{0, 5, 0, 5});
                players.add(player1);
                players.add(player2);
                break;
            case 3:
                player1 = new Player("Player 1", "Green", 1, new int[]{0, 0, 0, 0});
                player2 = new Player("Player 2", "Red", 2, new int[]{0, 3, 0, 3});
                player3 = new Player("Player 3", "Blue", 3, new int[]{0, 6, 0, 6});
                players.add(player1);
                players.add(player2);
                players.add(player3);
                grid.initGrid(player1, player2, player3);
                break;
            case 4:
                player1 = new Player("Player 1", "Green", 1, new int[]{0, 0, 0, 0});
                player2 = new Player("Player 2", "Red", 2, new int[]{0, 2, 0, 2});
                player3 = new Player("Player 3", "Blue", 3, new int[]{0, 5, 0, 5});
                player4 = new Player("Player 4", "Purple", 4, new int[]{0, 7, 0, 7});
                players.add(player1);
                players.add(player2);
                players.add(player3);
                players.add(player4);
                grid.initGrid(player1, player2, player3, player4);
                grid.displayGrid(grid.getLine(), grid.getColumn());
                break;
            default:
                System.out.println("Please choose a correct number of players (2, 3 or 4)");
        }
        //window = new Window(nbPlayer);
    }

    public void startGame() {
        switch (players.size()) {     //initialize the grid
            case 2:
                grid.initGrid(players.get(0), players.get(1));
                break;
            case 3:
                grid.initGrid(players.get(0), players.get(1), players.get(2));
                break;
            case 4:
                grid.initGrid(players.get(0), players.get(1), players.get(2), players.get(3));
                break;
            default:
                System.out.println("Error, number of players incorrect !");
        }
        int nbPlayers = players.size();
        int nbPlayersHaveWon = 0;
        int round = 0;
        grid.displayGrid(grid.getLine(), grid.getColumn());

        while (nbPlayersHaveWon < nbPlayers - 1) {
            ++round;
            System.out.println("  --  Round " + round + "  --");
            for (int i = 0; i < players.size(); i++) {
                if (!players.get(i).getHasWon()) {     //si le joueur a gagné, il ne joue plus
                    System.out.println("\nPlayer " + players.get(i).getPassageOrder() + " it's your turn !");
                    players.get(i).pickCardFromDeck();
                    players.get(i).play();
                    grid.updateGridWall(players.get(i));
                    grid.updateGridPlayers(grid.getLine(), grid.getColumn(), players);
                    grid.displayGrid(grid.getLine(), grid.getColumn());
                    
                    //window.refresh(grid.getGrid());
                    System.out.println();
                    if(players.get(i).getHasWon()){   //si le joueur a gagné pendant ce round
                        nbPlayersHaveWon++;
                    }
                }
            }
        }
    }

}
