package pojo;

public class User {
    private String username;
    private String nickname;
    private String image;
    private int articleNumber;
    private int categoryNumber;

    // 无参构造函数
    public User() {
    }

    // 带参数的构造函数
    public User(String username, String nickname, String image, int articleNumber, int categoryNumber) {
        this.username = username;
        this.nickname = nickname;
        this.image = image;
        this.articleNumber = articleNumber;
        this.categoryNumber = categoryNumber;
    }

    // Getter 和 Setter 方法
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(int articleNumber) {
        this.articleNumber = articleNumber;
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(int categoryNumber) {
        this.categoryNumber = categoryNumber;
    }
}
