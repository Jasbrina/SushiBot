package Main;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.concurrent.ThreadLocalRandom;


public class Minesweeper extends Command {
    public Minesweeper(){
        this.name = "ms";
        this.aliases = new String[]{"ms"};
        this.help = "Creates and prints a randomized minesweeper game.";
    }

    private String buildMine(){
        int WIDTH = 9, HEIGHT = 10;
        int[][] grid = new int[WIDTH][HEIGHT];
        boolean[][] bombGrid = new boolean[WIDTH][HEIGHT];
        int count = 0;

        //randomly assign bombs
        for (int y = 0; y < HEIGHT; y++){
            for (int x = 0; x < WIDTH; x++){
                int chance = ThreadLocalRandom.current().nextInt(1, 7 + 1);
                if (chance == 3) {
                    bombGrid[x][y] = true;
                    count++;
                }
                else bombGrid[x][y] = false;
            }
        }

        //create the actual board; traverses and checks 8 neighbours
        for (int y = 0; y < HEIGHT; y++){
            for (int x = 0; x < WIDTH; x++){
                int bombCount = 0;

                if (!bombGrid[x][y]) {
                    if (x - 1 >= 0 && y - 1 >= 0 && bombGrid[x - 1][y - 1]) bombCount++;
                    if (x - 1 >= 0 && bombGrid[x - 1][y]) bombCount++;
                    if (x - 1 >= 0 && y + 1 < HEIGHT && bombGrid[x - 1][y + 1]) bombCount++;
                    if (y - 1 >= 0 && bombGrid[x][y - 1]) bombCount++;
                    if (y+1 < HEIGHT && bombGrid[x][y+1]) bombCount++;
                    if (x+1 < WIDTH && y-1 >= 0 && bombGrid[x+1][y-1]) bombCount++;
                    if (x+1 < WIDTH && bombGrid[x+1][y]) bombCount++;
                    if (x+1 < WIDTH && y+1 < HEIGHT && bombGrid[x+1][y+1]) bombCount++;

                    grid[x][y] = bombCount;
                }
                else grid[x][y] = -1;
            }
        }

        String discordtemp = "";
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                String temp;
                switch(grid[x][y]){
                    case 0: temp = ":zero:"; break;
                    case 1: temp = ":one:"; break;
                    case 2: temp = ":two:"; break;
                    case 3: temp = ":three:"; break;
                    case 4: temp = ":four:"; break;
                    case 5: temp = ":five:"; break;
                    case 6: temp = ":six:"; break;
                    case 7: temp = ":seven:"; break;
                    case 8: temp = ":eight:"; break;
                    default: temp = ":bomb:"; break;
                }
                discordtemp += "||" + temp + "||";
                if (x == WIDTH - 1) discordtemp += "\n";
            }
        }
        return "" + "Bomb Count: " + count + "\n" + discordtemp;
    }

    @Override
    protected void execute(CommandEvent event){ event.reply(buildMine()); }
}
