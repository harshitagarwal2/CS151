public class LeapYear {
    private int year;

    public LeapYear(int year) {
        this.year = year;
    }

    public boolean isLeap() {
        if (year %4 == 0 && year %100 !=0 || year % 400 == 0)
            return true;
        return false;

    }
}