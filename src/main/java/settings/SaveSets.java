package settings;

public class SaveSets implements Runnable{
    private SetToJson settings;

    public SaveSets(SetToJson settings) {
        this.settings = settings;
    }

    @Override
    public void run() {
        settings.save();
    }
}
