package apps.nixonblkmnd.reminderapplication.objects;

//STORES CALENDAR DATES FROM DATABASE TO POPULATE CALENDAR
public class ObjectCalendar {
    private int id;
    private String remStartDate;

    //CONSTRUCTOR
    public ObjectCalendar(int id, String remStartDate) {
        this.id = id;
        this.remStartDate = remStartDate;
    }

    //TOSTRING
    @Override
    public String toString() {
        return "ObjectCalendar{" +
                "id=" + id +
                ", remStartDate='" + remStartDate + '\'' +
                '}';
    }

    //GET SET
    public int getId() {
        return id;
    } public void setId(int id) {
        this.id = id;
    }
    public String getRemStartDate() {
        return remStartDate;
    } public void setRemStartDate(String remStartDate) {
        this.remStartDate = remStartDate;
    }

}
