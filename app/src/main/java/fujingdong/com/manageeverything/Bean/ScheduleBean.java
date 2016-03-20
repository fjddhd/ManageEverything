package fujingdong.com.manageeverything.Bean;

/**
 * Created by Administrator on 2016/3/12.
 */
public class ScheduleBean {
    public String title;
    public String content;
    public int progress;
    public int progressMax;
    public int id;

    @Override
    public String toString() {
        return "ScheduleBean{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", progress=" + progress +
                ", progressMax=" + progressMax +
                ", id=" + id +
                '}';
    }

    public ScheduleBean() {
//        this.title = title;
//        this.content = content;
//        this.progress = progress;
//        this.progressMax = progressMax;
//        this.id=id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setProgressMax(int progressMax) {
        this.progressMax = progressMax;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getProgress() {
        return progress;
    }

    public int getProgressMax() {
        return progressMax;
    }

    public int getId() {
        return id;
    }
}
