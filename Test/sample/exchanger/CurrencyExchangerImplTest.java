package sample.exchanger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class CurrencyExchangerImplTest {
    private static CurrencyExchangerImpl currencyExchanger;

    @BeforeAll
    static void initialize() {
        currencyExchanger = new CurrencyExchangerImpl();
    }

    @Test
    void shouldCorrectlyMultiplyValues() {
        //given
        BigDecimal value = new BigDecimal("1.5");
        Integer amount = 2;
        //when
        BigDecimal result = currencyExchanger.exchangeCurrency(value, amount);
        //then
        assertThat(result, is(new BigDecimal("3.00")));
    }
}