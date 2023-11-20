package ru.consulting.Count.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.consulting.Count.service.StringCalculateService;

import java.util.Map;

@RestController
@RequestMapping("/count")
@Tag(name = "Get request", description = "Makes a get request with a string, receives the number of characters in the line as a response.")
public class StringCalculateController {

    @Autowired
    private StringCalculateService calculateService;

    @GetMapping("/get/{key}")
    @Operation(
            summary = "Decomposing a string into characters",
            description = "Allows you to find out the number of characters in a line, arranged in descending order"
    )
    public Map<Character, Long> findSymbols(@PathVariable String key) {
        return calculateService.calculate(key);
    }
}

