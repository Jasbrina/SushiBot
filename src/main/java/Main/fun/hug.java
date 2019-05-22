package fun;

import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.entity.search.SearchFeed;
import at.mukprojects.giphy4j.exception.GiphyException;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.util.concurrent.ThreadLocalRandom;

public class hug extends Command {

    public hug(){
        this.name = "hug";
        this.aliases = new String[]{"hug"};
        this.help = "Use -hug @<username> to hug someone!.";
    }

    @Override
    public void execute(CommandEvent commandEvent) {

        Giphy giphy = new Giphy("NcRZbVFPiZ16aBnxT5zzE1m6R5xPPRCj");
        String url = "";
        int size = 0;
        try {
            SearchFeed sf = giphy.search("anime hug", 0, ThreadLocalRandom.current().nextInt(0, 100 + 1));
            url = sf.getDataList().get(0).getImages().getOriginal().getUrl();
        }catch (GiphyException g){
            g.printStackTrace();
        }

        EmbedBuilder b = new EmbedBuilder();
        b.setImage(url);
        b.setDescription(commandEvent.getAuthor().getName()+" has hugged " + commandEvent.getMessage()
         .getMentionedUsers().get(0).getName());
        commandEvent.reply(b.build());
    }
}
