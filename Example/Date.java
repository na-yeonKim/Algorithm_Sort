package Example;

public class Date implements Comparable<Date> {
    private final int month;
    private final int day;
    private final int year;
    public Date(int month, int day, int year) {
        if(!isValid(month, day, year)) throw new IllegalArgumentException("Invalid date");
        this.month = month;
        this.day = day;
        this.year = year;
    }
    public int compareTo(Date that) {
        if(this.year < that.year) return -1;
        if(this.year > that.year) return +1;
        if(this.month < that.month) return -1;
        if(this.month > that.month) return +1;
        if(this.day < that.day) return -1;
        if(this.day > that.day) return +1;
        return 0;
    }
    public String toString() {
        return String.format("%d / %d / %d", month, day, year);
    }
    private static boolean isValid(int month, int day, int year) {
        if (month < 1 || month > 12) return false;
        if (day < 1 || day > daysInMonth(month, year)) return false;
        return year >= 1; // 일반적인 양수 연도 조건
    }
    private static int daysInMonth(int month, int year) {
        switch (month) {
            case 2: return isLeapYear(year) ? 29 : 28;
            case 4: case 6: case 9: case 11: return 30;
            default: return 31;
        }
    }
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static void main(String[] args) {
        Date date = new Date(3, 16, 2025);
        System.out.println(date);
    }
}