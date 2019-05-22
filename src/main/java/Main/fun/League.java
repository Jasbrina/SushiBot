package Main.fun;

import Main.Secret.Secret;
import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.entity.search.SearchFeed;
import at.mukprojects.giphy4j.exception.GiphyException;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.util.concurrent.ThreadLocalRandom;

public class League extends Command {

    public League(){
        this.name = "league";
        this.aliases = new String[]{"ihateleague", "smash"};
        this.help = "Angry that you lost a match? Use -league!";
    }

    @Override
    public void execute(CommandEvent commandEvent) {
        Secret secret = new Secret();
        Giphy giphy = new Giphy(secret.getGiphykey());
        String url = "";
        try {
            SearchFeed sf = giphy.search("rage keyboard", 0, ThreadLocalRandom.current().nextInt(0, 48 + 1));
            url = sf.getDataList().get(0).getImages().getOriginal().getUrl();
        }catch (GiphyException g){
            g.printStackTrace();
        }

        EmbedBuilder b = new EmbedBuilder();
        b.setImage(url);
        b.setDescription(commandEvent.getAuthor().getName() + " hates League.");
        commandEvent.reply(b.build());

    }

}
