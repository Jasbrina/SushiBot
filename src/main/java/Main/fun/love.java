package fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.User;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
//from  www.java2s.com
import javax.imageio.ImageIO;

public class love extends Command {
    public love(){
        this.name = "l";
        this.aliases = new String[]{"l"};
        this.help = "Use -l @<username1> @<username2> to make a ship!";
    }

    public static BufferedImage joinBufferedImage(BufferedImage img1,
                                                  BufferedImage img2) {
        int offset = 0;
        int width = img1.getWidth() + img2.getWidth() + offset;
        int height = Math.max(img1.getHeight(), img2.getHeight()) + offset;
        BufferedImage newImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        g2.setPaint(Color.BLACK);
        g2.fillRect(0, 0, width, height);
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, img1.getWidth() + offset, 0);
        g2.dispose();
        return newImage;
    }

    public static void downloadImage(String u, String filename) throws IOException{
        final String saveDir = ("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot\\sushibot\\src\\main\\java\\fun\\images");
        final int BUFFER_SIZE = 4096;

        URL url = new URL(u);
        String fileName = filename + ".png";

        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        InputStream inputStream = connection.getInputStream();
        String saveFilePath = saveDir + File.separator + fileName;

        FileOutputStream outputStream = new FileOutputStream(saveFilePath);
        int bytesRead = -1;
        byte[] buffer = new byte[BUFFER_SIZE];
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        outputStream.close();
        inputStream.close();

        System.out.println("File downloaded");
        connection.disconnect();
    }

    @Override
    public void execute(CommandEvent commandEvent) {
        String user1im, user2im;
        user1im = commandEvent.getMessage().getMentionedUsers().get(0).getAvatarUrl();
        user2im = commandEvent.getMessage().getMentionedUsers().get(1).getAvatarUrl();

        try{
            downloadImage(user1im, "im1");
            downloadImage(user2im, "im2");

            //========= 1st join ====

            BufferedImage img1 = ImageIO.read(new File("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot" +
                    "\\sushibot\\src\\main\\java\\fun\\images\\im1.png"));
            BufferedImage img2 = ImageIO.read(new File("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot" +
                    "\\sushibot\\src\\main\\java\\fun\\images\\love.png"));
            BufferedImage joinedImg = joinBufferedImage(img1, img2);

            ImageIO.write(joinedImg, "png", new File("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot\\sushibot" +
                    "\\src\\main\\java\\fun\\images\\image1.png"));

            //========= 2nd join ====

            BufferedImage img3 = ImageIO.read(new File("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot\\sushibot" +
                    "\\src\\main\\java\\fun\\images\\image1.png"));

            BufferedImage img4 = ImageIO.read(new File("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot" +
                    "\\sushibot\\src\\main\\java\\fun\\images\\im2.png"));

            BufferedImage joinedImg2 = joinBufferedImage(img3, img4);
            ImageIO.write(joinedImg2, "png", new File("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot" +
                    "\\sushibot" +
                    "\\src\\main\\java\\fun\\images\\image2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }

        commandEvent.getChannel().sendFile(new File("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot" +
                "\\sushibot\\src\\main\\java\\fun\\images\\image2.png")).queue();

    }

    public void jendy(String user1im, String user2im) {
        try{
            downloadImage(user1im, "im1");
            downloadImage(user2im, "im2");

            //========= 1st join ====

            BufferedImage img1 = ImageIO.read(new File("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot" +
                    "\\sushibot\\src\\main\\java\\fun\\images\\im1.png"));
            BufferedImage img2 = ImageIO.read(new File("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot" +
                    "\\sushibot\\src\\main\\java\\fun\\images\\love.png"));
            BufferedImage joinedImg = joinBufferedImage(img1, img2);

            ImageIO.write(joinedImg, "png", new File("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot\\sushibot" +
                    "\\src\\main\\java\\fun\\images\\image1.png"));

            //========= 2nd join ====

            BufferedImage img3 = ImageIO.read(new File("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot\\sushibot" +
                    "\\src\\main\\java\\fun\\images\\image1.png"));

            BufferedImage img4 = ImageIO.read(new File("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot" +
                    "\\sushibot\\src\\main\\java\\fun\\images\\im2.png"));

            BufferedImage joinedImg2 = joinBufferedImage(img3, img4);
            ImageIO.write(joinedImg2, "png", new File("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot" +
                    "\\sushibot" +
                    "\\src\\main\\java\\fun\\images\\imagej.png"));

        }catch(IOException e){
            e.printStackTrace();

        }

    }


}
