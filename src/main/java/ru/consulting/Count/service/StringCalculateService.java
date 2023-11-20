package ru.consulting.Count.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class StringCalculateService {

    public Map<Character, Long> calculate(String key) {

        Map<Character, Long> mapCount = new HashMap<>();

        if (key == null) {
            throw new RuntimeException("invalid input data");
        }


        for (int i = 0; i < key.length(); i++) {
            char init = key.charAt(i);
            long count = key.chars().filter(c -> c == init).count();

            mapCount.put(init, count);
        }

        mapCount = mapCount.entrySet().stream()
                .sorted(Map.Entry.<Character, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new
                ));

        return mapCount;
    }
}
