package sample.updateDate;

import org.junit.jupiter.api.Test;
import sample.repository.RepositoryImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UpdateDateImplTest {


    @Test
    void methodShouldThrownNullPointerExceptionIfParameterIsLessThanZero() {
        //given
        //when
        UpdateDateImpl updateDate = new UpdateDateImpl();
        Long time = new Long(-1212122121);
        Exception exception = assertThrows(NumberFormatException.class, () -> updateDate.lastUpdate(time));
        String message = "Value can't be less than 0";
        String receivedMessage = exception.getMessage();
        //then
        assertTrue(message.contains(receivedMessage));
    }
}