package Pixiv;

public class PixivStatsImg {
    private String url, username, profileUrl, caption;
    private int ID, views, favc, score, rank;

    PixivStatsImg(String url, String username, String profileUrl, int ID, int views, int favc, int score, String cap,
                  int rank){
        this.url = url;
        this.username = username;
        this.profileUrl = profileUrl;
        this.ID = ID;
        this.views = views;
        this.favc = favc;
        this.score = score;
        this.caption = cap;
        this.rank = rank;
    }

    public String getUrl(){return url;}
    public String getUsername(){return username;}
    public String getProfileUrl(){return profileUrl;}
    public int getID(){return ID;}
    public int views(){return views;}
    public int favc(){return favc;}
    public int getScore(){return score;}
    public String getCaption(){return caption;}
    public int getRank(){return rank;}

}
