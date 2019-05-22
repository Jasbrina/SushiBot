package Main.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Ask extends Command {

    public Ask(){
        this.name = "ask";
        this.aliases = new String[]{"ask"};
        this.help = "Use -ask to ask the magic 8ball a question!";
    }

    @Override
    public void execute(CommandEvent commandEvent) {
        if (commandEvent.getMessage().getContentRaw().length() <= 4){
            commandEvent.getChannel().sendMessage("Please specify a question!").queue();
        }
        else {
            int i = ThreadLocalRandom.current().nextInt(0, 19 + 1);
            String answer = null;
            switch (i){
                case 0: answer = "It is certain."; break;
                case 1: answer= "It is decidedly so."; break;
                case 2: answer = "Without a doubt."; break;
                case 3: answer = "Yes- definitely."; break;
                case 4: answer = "You may rely on it."; break;
                case 5: answer = "As I see it, yes."; break;
                case 6: answer = "Most likely."; break;
                case 7: answer = "Outlook good."; break;
                case 8: answer = "Yes."; break;
                case 9: answer = "Signs point to yes."; break;

                case 10: answer = "Reply hazy, try again."; break;
                case 11: answer = "Ask again later."; break;
                case 12: answer = "Better not tell you now."; break;
                case 13: answer = "Cannot predict now."; break;
                case 14: answer = "Concentrate and ask again."; break;

                case 15: answer = "Don't count on it."; break;
                case 16: answer = "My reply is no."; break;
                case 17: answer = "My sources say no."; break;
                case 18: answer = "Outlook not so good."; break;
                case 19: answer = "Very doubtful."; break;
            }
            EmbedBuilder b= new EmbedBuilder();
            if (i >= 0 && i <= 9) b.setColor(Color.GREEN);
            if (i >= 10 && i <= 14) b.setColor(Color.yellow);
            if (i >= 15 && i <= 19) b.setColor(Color.red);
            b.appendDescription(answer);

            commandEvent.getChannel().sendMessage(b.build()).queue();
        }
    }
}
