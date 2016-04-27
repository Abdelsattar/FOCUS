package abdelsattar.com.focus.Model;

/**
 * Created by Abd El-Sattar on 4/27/2016.
 */
public class ThankfulFor {
    long id ;
    String thankfulFor;

    public ThankfulFor() {
        this.id = 0;
        this.thankfulFor = null;
    }

    public ThankfulFor(String thankfulFor){
        this.thankfulFor = thankfulFor;
    }

    public ThankfulFor(long id, String thankfulFor) {
        this.id = id;
        this.thankfulFor = thankfulFor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getThankfulFor() {
        return thankfulFor;
    }

    public void setThankfulFor(String thankfulFor) {
        this.thankfulFor = thankfulFor;
    }
}
