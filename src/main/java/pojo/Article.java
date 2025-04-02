package pojo;

import java.sql.Date;

public class Article {
    private Integer id;
    private String title; // 标题
    private String text; // 正文
    private String summary; // 摘要
    private Date time; // 时间

    // 无参构造函数
    public Article() {
    }

    // 带参构造函数
    public Article(String title, String text, String summary, Date time) {
        this.title = title;
        this.text = text;
        this.summary = summary;
        this.time = time;
    }

    // Getter 和 Setter 方法

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}
