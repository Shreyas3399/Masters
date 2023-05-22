import java.util.Comparator;
/**
 *Implementing Address class which extends Comparable
 *and overriding compareTO
 *@author Shireen Maini
 *@author Shreyas Belkune
 */
public class LP implements Comparable<LP> {
    int year;
    String title;
    String artist;
    float length;
    int tracks;
    public LP(int year, String title, String artist, float length, int tracks)
    {
        this.year=year;
        this.title=title;
        this.artist=artist;
        this.length=length;
        this.tracks=tracks;
    }
    public String toString(){//overriding the toString() method
        return year+" "+title+" "+artist+" "+length+" "+tracks;
    }

    public int compareTo(LP o1) {
        return this.year - o1.year;//Comparing based on year
    }

}
