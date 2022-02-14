package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import javax.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
@DisplayName("AppController")
public class AppControllerTest {

    @InjectMock
    transient AppService appService;

    @Inject
    transient AppController appController;

    @Test
    @DisplayName("#hello returns 'Hello!' message")
    void helloMethod() {
        when(appService.hello()).thenReturn("Hello!");

        assertEquals("Hello!", appController.hello());
    }
}
