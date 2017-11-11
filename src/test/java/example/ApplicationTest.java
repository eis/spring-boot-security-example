package example;

import example.hello.HelloMVCController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void contextLoads() throws Exception {
        assertNotNull(applicationContext);
        assertNotNull(applicationContext.getBean(HelloMVCController.class));
    }
}
