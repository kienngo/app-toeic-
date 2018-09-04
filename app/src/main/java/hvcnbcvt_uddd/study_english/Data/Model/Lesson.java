package hvcnbcvt_uddd.study_english.Data.Model;

public class Lesson {
    private int mId;
    private String mName;
    private String mDescription;

    public Lesson(int id, String name, String description) {
        mId = id;
        mName = name;
        mDescription = description;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

}
