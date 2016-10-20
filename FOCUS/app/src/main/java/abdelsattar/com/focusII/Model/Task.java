package abdelsattar.com.focusII.Model;

/**
 * Created by lenovo on 25/03/2016.
 */
public class Task {
    long id ;
    String task;

    public Task() {
        this.id = 0;
        this.task = null;
    }

    public Task(String task){
        this.task = task;
    }

    public Task(long id, String task) {
        this.id = id;
        this.task = task;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
