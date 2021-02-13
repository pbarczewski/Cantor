package sample.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sample.entity.CurrencyEntity;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RepositoryImplTest {
    private static RepositoryImpl repository;

    @BeforeAll
    static void initialize() {
        repository = new RepositoryImpl();
        try {
            repository.getData("EUR");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void shouldThrownExceptionIfBaseCurrencyIsIncorrect() {
        //given
        //when
        String url = "ABCDE";
        //then
        try {
            assertThrows(IllegalArgumentException.class, () -> repository.getData(url));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void mapWithCurrenciesShouldHasSizeGreaterThanZeroIfRepositoryReadCorrectly() {
        //given
        String url = "EUR";
        Map<String, Double> mapOfCurrencies = new HashMap<>();
        //when
        try {
            mapOfCurrencies =repository.getData(url);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //then
        assertThat(mapOfCurrencies.size(), greaterThan(0));
    }

    @Test
    void valueShouldNotBeNullIfSearchingCurrencyIsCorrect() {
        //given
        String currency = "PLN";
        //then
        BigDecimal selectedCurrency = repository.findCurrency(currency);
        //when
        assertThat(selectedCurrency, is(notNullValue()));
    }

    @Test
    void findCurrencyMethodShouldThrownExceptionIfSearchingCurrencyIsIncorrect() {
        //given
        //when
        String currency = "ABCDEFG";
        //then
        try {
         assertThrows(NullPointerException.class, () -> repository.findCurrency(currency));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void mapOfCurrenciesCodeShouldContainsCorrectValuesAfterCallingGetCodesMethod() {
        //given
        List<String> codes = new ArrayList<>();
        Map<String, Double> data;
        //when
        try {
          data = repository.getData("EUR");
          codes = repository.getCodes(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //then
        assertThat(codes, hasItems("PLN"));
    }

    @Test
    void shouldThrownExceptionIfMapOfCurrenciesCodesReadDataIncorrectly() {
        //given
        //when
        List<String> codes = new ArrayList<>();
        Map<String, Double> data = new HashMap<>();
        //then
        assertThrows(Exception.class, () -> repository.getCodes(data));
    }
}