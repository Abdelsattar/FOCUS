package abdelsattar.com.focus.Model;

/**
 * Created by lenovo on 25/03/2016.
 */
public class Task {
    int id ;
    String task;

    public Task() {
        this.id = 0;
        this.task = null;
    }

    public Task(int id, String task) {
        this.id = id;
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
