package fujingdong.com.manageeverything.Bean;

/**
 * Created by Administrator on 2016/3/12.
 */
public class ScheduleBean {
    public String title;
    public String content;
    public int progress;
    public int progressMax;

    public ScheduleBean(String title, String content, int progress, int progressMax) {
        this.title = title;
        this.content = content;
        this.progress = progress;
        this.progressMax = progressMax;
    }
}
