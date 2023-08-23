package com.alaindroid.sportsbet;

import com.alaindroid.sportsbet.api.model.OrderRequest;
import com.alaindroid.sportsbet.api.model.OrderResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FromFileBlackBoxTests {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @SneakyThrows
    void runOriginalTestCase() {
        String url = "http://localhost:" + port + "/order";
        List<TestCase> testCases = loadTestCase();
        System.out.println(testCases);
        for (TestCase testCase: testCases) {
            ResponseEntity<OrderResponse> response = restTemplate.postForEntity(url,
                    testCase.request(),
                    OrderResponse.class);
            System.out.println(response.getBody());
            assertThat(response.getBody())
                    .usingComparatorForType(BigDecimal::compareTo, BigDecimal.class)
                    .usingRecursiveComparison()
                    .isEqualTo(testCase.expectedResponse);
        }
    }

    private List<TestCase> loadTestCase() throws FileNotFoundException {
        String testCaseFile = "original-case.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(testCaseFile).getFile());
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(file.getAbsolutePath()));
        return gson.fromJson(reader, new TypeToken<List<TestCase>>() {}.getType());
    }

    public record TestCase (OrderRequest request, OrderResponse expectedResponse) { }

}
