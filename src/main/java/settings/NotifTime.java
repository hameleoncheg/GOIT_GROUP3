package settings;

public enum NotifTime {
    NINE (9, false),
    TEN (10,false),
    ELEVEN (11,false),
    TWELVE (12,false),
    THIRTEEN (13,false),
    FOURTEEN (14,false),
    FIFTEEN (15,false),
    SIXTEEN (16,false),
    SEVENTEEN (17,false),
    EIGHTEEN (18,false),
    SWICH_OFF (0,false);

    private int time;
    private boolean select;

    NotifTime(int time, boolean select) {
        this.time = time;
        this.select = select;
    }

    public int getTime() {
        return time;
    }


    public static NotifTime convertToEnum (String text){
        for (NotifTime time: NotifTime.values()) {
            if (String.valueOf(time.getTime()).equals(text)) {
                return time;
            }
        }
        return null;
    }
}
