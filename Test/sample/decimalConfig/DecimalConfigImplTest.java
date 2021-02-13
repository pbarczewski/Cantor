package sample.decimalConfig;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


class DecimalConfigImplTest {
    private static DecimalConfigImpl decimalConfig;

    @BeforeAll
    static void initialize() {
        decimalConfig = new DecimalConfigImpl();
    }

    @Test
    void numberShouldBeCorrectlyFormatted() {
        //given
        BigDecimal bigDecimal = new BigDecimal("3.388888");
        //then
        BigDecimal formattedValue = decimalConfig.configuration(bigDecimal);
        //when
        assertThat(formattedValue, is(new BigDecimal("3.39")));
    }
}