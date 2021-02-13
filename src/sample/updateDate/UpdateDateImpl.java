package sample.updateDate;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class UpdateDateImpl implements UpdateDate {
    @Override
    public String lastUpdate(long time) {
        if (time > 0) {
            return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date(time * 1000));
        } else {
            throw new NumberFormatException("Value can't be less than 0");
        }
    }
}