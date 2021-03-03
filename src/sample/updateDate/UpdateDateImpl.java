package sample.updateDate;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * This class implements UpdateDate interface
 *
 */
public class UpdateDateImpl implements UpdateDate {
    /**
     * The method accepts time and returns formatted date
     *
     * <p>
     * It returns SimpleDateFormat if time is correct, otherwise NumberFormatException is thrown
     * </p>
     *
     * @param time  the time parameter
     * Returns formatted date
     */
    @Override
    public String lastUpdate(long time) {
        if (time > 0) {
            return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date(time * 1000));
        } else {
            throw new NumberFormatException("Value can't be less than 0");
        }
    }
}