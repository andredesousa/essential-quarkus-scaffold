package app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
@DisplayName("AppService")
public class AppServiceTest {

    @Inject
    transient AppService service;

    @Test
    @DisplayName("#hello returns 'Hello World!' message")
    void helloMethod() {
        assertEquals("Hello World!", service.hello());
    }
}
