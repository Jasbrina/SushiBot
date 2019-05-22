package Pixiv;
import Secret.Secret;
import com.scienjus.client.PixivParserClient;
import com.scienjus.model.*;
import com.scienjus.param.ParserParam;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.net.URL;
import java.io.File;
import java.net.HttpURLConnection;

public class Pixiv{
    Secret secret;
    private static PixivParserClient client;

    private String USERNAME;
    private String PASSWORD;
    final static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) "
            + "Chrome/74.0.3729.131 Safari/537.36";
    final static String saveDir = ("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot\\sushibot\\src\\main" +
            "\\java\\Pixiv\\images");
    final static int BUFFER_SIZE = 4096;

    public Pixiv(){
        secret  = new Secret();
        USERNAME = secret.getPixivu();
        PASSWORD = secret.getPixivp();
        System.out.println(USERNAME);
        System.out.println(PASSWORD);
        client = new PixivParserClient();
        client.setUsername(USERNAME);
        client.setPassword(PASSWORD);
    }

    public static void downloadImage(String u, int nf) throws IOException{
        URL url = new URL(u);
        String fileName = nf + ".jpg";

        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.addRequestProperty("REFERER", "http://www.pixiv.net");

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

    //==================================================================
    /*
        General purpose methods.
     */

    // @return size of works collection
    public static int getWorkSize(String keyword){
        int ret = 0;
        if (client.login()){
            List<Work> works = client.search(keyword);
            ret = works.size();
        }
        return ret;
    }

    public static int getWorkSizeRanking(){
        int ret = 0;
        if (client.login()){
            List<RankWork> works = client.ranking().getWorks();
            ret = works.size();
        }
        return ret;
    }

    /*
        @param keyword: search term keyword
        @param k: index of image to get
        @return PixivStatsImg: contains various details about the image, as well as url to image
     */
    public static PixivStatsImg search(String keyword, int k){
        if (client.login()) {
            System.out.println("Login successful.");
            List<Work> works = client.search(keyword);
            String url = works.get(k).getImageUrls().getSmall();

            User user = works.get(k).getUser();
                String username = user.getName();
                ProfileImageUrls piu = user.getProfileImageUrls();
                String PU = piu.getPx50x50();
                int id = user.getId();
                String u_stats = user.getStats();

            Stats stats = works.get(k).getStats();
                String cap = works.get(k).getCaption();
                int views = stats.getViewsCount();
                FavoritedCount fc = stats.getFavoritedCount();
                int f = fc.getPublicCount()+fc.getPrivateCount();
                int score = stats.getScore();

            PixivStatsImg ps = new PixivStatsImg(url,username,PU,id,views,f,score, cap, 0);
            client.close();
            return ps;

        }else {
            System.out.println("ERROR");
            return null;
        }

    }

    public static PixivStatsImg rankings(int k){
        if (client.login()){
            List<RankWork> works = client.ranking().getWorks();
            String url = works.get(k).getWork().getImageUrls().getSmall();

            User user = works.get(k).getWork().getUser();
                String cap = client.ranking().getWorks().get(k).getWork().getCaption();
                String username = user.getName();
                ProfileImageUrls piu = user.getProfileImageUrls();
                String PU = piu.getPx50x50();
                int id = user.getId();
                String u_stats = user.getStats();


            Stats stats = works.get(k).getWork().getStats();
                int views = stats.getViewsCount();
                FavoritedCount fc = stats.getFavoritedCount();
                int f = fc.getPublicCount()+fc.getPrivateCount();
                int score = stats.getScore();
                int rank = works.get(k).getRank();

            PixivStatsImg ps = new PixivStatsImg(url,username,PU,id,views,f,score,cap, rank);
            client.close();
            return ps;
        }
        else return null;
    }

}