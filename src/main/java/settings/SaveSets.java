package settings;

public class SaveSets implements Runnable{
    @Override
    public void run() {
        SetToJson.save();
    }
}
