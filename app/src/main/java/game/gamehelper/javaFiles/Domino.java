package game.gamehelper.javaFiles;

import android.os.Parcel;
import android.os.Parcelable;

import game.gamehelper.R;
/**
 * Author History:
 * Jacob
 * Mark
 * Jacob
 */

/**
 * Created by Jacob on 2/11/2015.
 * A domino: defined as value 1, value 2, and sum of values.
 */
public class Domino implements Parcelable {
    private final int val1;
    private final int val2;
    private final int sum;

    @Override
    //required for Parcelable
    public int describeContents() {
        return 0;
    }

    /**
     * Constructor. Creates the Domino.
     * @param value1 Pair value 1.
     * @param value2 Pair value 2.
     */
    public Domino(int value1, int value2) {
        val1 = value1;
        val2 = value2;
        sum = getVal1() + getVal2();

    }

    public int getVal1() {
        return val1;
    }

    public int getVal2() {
        return val2;
    }

    public int getSum() {
        return sum;
    }

    //Allows comparison between other dominoes
    public boolean compareTo(Domino a) {
        if (val1 == a.getVal1() && val2 == a.getVal2())
            return true;
        if (val1 == a.getVal2() && val2 == a.getVal1())
            return true;
        return false;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(val1);
        dest.writeInt(val2);
    }


}
