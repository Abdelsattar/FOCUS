package abdelsattar.com.focus.Model;

/**
 * Created by lenovo on 25/03/2016.
 */
public class SubTask {
    long id, parent_ID;
    String subTask;

    public SubTask() {
        this.id = 0;
        this.parent_ID = 0;
        this.subTask = null;
    }

    public SubTask(long parent_ID, String subTask) {
        this.parent_ID = parent_ID;
        this.subTask = subTask;
    }

    public SubTask(long id, long parent_ID, String subTask) {
        this.id = id;
        this.parent_ID = parent_ID;
        this.subTask = subTask;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getParent_ID() {
        return parent_ID;
    }

    public void setParent_ID(int parent_ID) {
        this.parent_ID = parent_ID;
    }

    public String getSubTask() {
        return subTask;
    }

    public void setSubTask(String subTask) {
        this.subTask = subTask;
    }
}
