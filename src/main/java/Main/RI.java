package Main;

import Main.ArtistDatabase.Lookup;
import Main.Pixiv.Pixiv;
import Main.Pixiv.PixivStatsImg;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RI extends Command {
    Lookup p;
    List<Object> hm = null;
    public RI(){
        this.name = "pixiv";
        this.aliases = new String[]{"pixiv"};
        this.help = "Inquire for a random Main.Pixiv image. Usage: " +"\n" +
                "-pixiv <searchterm> : search using a keyword." + "\n" +
                "-pixiv ranking : inquire a random image from the daily ranking boards. ";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        String inquiry = commandEvent.getMessage().getContentRaw();
        String inq = inquiry.substring(7, inquiry.length());

        commandEvent.getEvent().getMessage().addReaction("\u2611").queue();
        commandEvent.getChannel().sendTyping().queue();

        Pixiv pt = new Pixiv();
        String u = "";

        PixivStatsImg p;

        if (inq.equals("ranking")) {
            int ret = pt.getWorkSizeRanking();
            p = pt.rankings(ThreadLocalRandom.current().nextInt(0, ret + 1));

        } else {

            int ret = pt.getWorkSize(inq);
            p = pt.search(inq, ThreadLocalRandom.current().nextInt(0, ret + 1));
        }

        u = p.getUrl();
        String x = "https://i.pximg.net/" + u.substring(30, u.length());

        try {
            pt.downloadImage(x, 0);                 //download main image
            pt.downloadImage(p.getProfileUrl(), 1);  //download profile pic
        } catch (IOException e) {
            e.printStackTrace();
        }

        File f = new File("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot\\sushibot\\src\\main\\java" +
                "\\Main.Pixiv\\images\\0.jpg");


        //build EmbedBuilder
        EmbedBuilder b = new EmbedBuilder();
        b.setColor(Color.cyan);
        b.setAuthor(p.getUsername());
        b.setThumbnail("attachment://1.jpg");
        b.setDescription("");
        if (inq.equals("ranking"))
            b.appendDescription(
                    "Pofile: " + "http://www.pixiv.net/member.php?id=" + p.getID() + "\n" +
                            "Views:  " + Integer.toString(p.views()) + "\n" +
                            "Favorites:  " + p.favc() + "\n" +
                            "Score:  " + Integer.toString(p.getScore()) + "\n" +
                            "Rank:  " + p.getRank() + "\n" +
                            "Caption: " + p.getCaption()

            );
        else b.appendDescription(
                "Pofile: " + "http://www.pixiv.net/member.php?id=" + p.getID() + "\n" +
                        "Views:  " + Integer.toString(p.views()) + "\n" +
                        "Favorites:  " + p.favc() + "\n" +
                        "Score:  " + Integer.toString(p.getScore()) + "\n" +
                        "Caption: " + p.getCaption()

        );

        MessageChannel channel = commandEvent.getChannel();
        MessageBuilder message = new MessageBuilder();
        message.setEmbed(b.build());
        channel.sendFile(new File("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot\\sushibot\\src\\main" +
                "\\java\\Main.Pixiv\\images\\1.jpg"), message.build()).queue();

        commandEvent.getChannel().sendFile(f).queue();

    }
}
