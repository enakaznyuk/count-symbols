package ru.consulting.Count.service;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StringCalculateServiceTest {

    @Autowired
    private StringCalculateService calculateService;

    @Test
    void shouldFindSymbolsFromString() {
        Map<Character, Long> result = calculateService.calculate("ttt22");
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.entrySet());
        Assertions.assertEquals(2, result.entrySet().size());
        assertTrue(result.containsKey('t'));
        Assertions.assertEquals(3, result.get('t'));
        Assertions.assertEquals(2, result.get('2'));
        System.out.println("input = " + result);
    }

    @Test
    void shouldCollectSymbolsDescendingOrders() {

        Map<Character, Long> result = calculateService.calculate("12312312333");

        Map<Character, Long> expected = new HashMap<>();
        expected.put('3', 5L);
        expected.put('1', 3L);
        expected.put('2', 3L);
        expected = expected.entrySet().stream()
                .sorted(Map.Entry.<Character, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new
                ));
        System.out.println("result = " + result);
        System.out.println("expected = " + expected);

        Assertions.assertEquals(result.keySet(), expected.keySet());
    }

    @ParameterizedTest
    @NullSource
    void shouldMethodGetNull(String word) {
        assertNull(calculateService.calculate(word));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "AAaa",
            ".../.,/.,/.,",
            "29871623987198",
            "jhfj bjyjh ghfvjhtfuyt uytdtrwred"
    })
    void shouldMethodWorkWithDifferentString(String word) {
        Map<Character, Long> result = calculateService.calculate(word);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.entrySet());
        System.out.println(result);
    }
}
