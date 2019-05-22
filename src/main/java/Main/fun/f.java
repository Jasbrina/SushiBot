package Main.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import java.io.*;

public class f extends Command {
    public f(){
        this.name = "f";
        this.aliases = new String[]{"f"};
        this.help = "Press -f to pay respects.";
    }

    @Override
    public void execute(CommandEvent commandEvent) {
        int fCount = 0;
        int userFCount = 0;
        File inputFile = new File("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot\\sushibot\\src\\main\\java\\Main\\fun\\f.txt").getAbsoluteFile();
        String userID = commandEvent.getAuthor().getId();
        File userF;

        userF = new File("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot\\sushibot\\src\\main\\java" +
                "\\Main\\fun\\" + userID + ".txt");

        System.out.println(userID);

        //get old f count
        String line = null;
        String sline = null;
        try{
            BufferedReader newReader;
            if (!userF.exists()) {
                userF.createNewFile();
                userFCount = 1;
            }else{
                newReader = new BufferedReader(new FileReader(userF));
                sline = newReader.readLine();
                userFCount = Integer.parseInt(sline);
                System.out.println("USER F " +userFCount);
            }

            userF = new File("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot\\sushibot\\src\\main\\java" +
                    "\\Main\\fun\\" + userID + ".txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(userF));
            bw.write(Integer.toString(userFCount+1));
            bw.close();

            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null){
                fCount = Integer.parseInt(line);
            }
            bufferedReader.close();

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException o){
            o.printStackTrace();
        }

        fCount++;

        EmbedBuilder b = new EmbedBuilder();
        String name = commandEvent.getAuthor().getName();
        b.appendDescription("**" +name+"**" + " has paid respects.");
        b.setFooter("Server total: " + fCount + ", " + commandEvent.getAuthor().getName() +" total: " + userFCount,
                null);
        commandEvent.getChannel().sendMessage(b.build()).queue();

        //update f count
        int lf = fCount - 1;
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(inputFile));
            inputFile = new File("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot\\sushibot\\src\\main\\java\\Main\\fun\\f.txt");
            bufferedWriter.write(Integer.toString(fCount++));
            bufferedWriter.close();

        }catch (IOException i){
            i.printStackTrace();
        }
    }
}
