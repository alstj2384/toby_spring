package springbook.learningtest.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;


@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class JUnitTest {
    @Autowired ApplicationContext context;
    static Set<JUnitTest> testObject = new HashSet<>();
    static ApplicationContext contextObject = null;
    @Test
    public void test1(){
        Assertions.assertFalse(testObject.contains(this));
        testObject.add(this);

        Assertions.assertTrue(contextObject == null || contextObject == this.context);
        contextObject = this.context;
    }

    @Test
    public void test2(){
        Assertions.assertFalse(testObject.contains(this));
        testObject.add(this);

        Assertions.assertTrue(contextObject == null || contextObject == this.context);
        contextObject = this.context;
    }

    @Test
    public void test3(){
        Assertions.assertFalse(testObject.contains(this));
        testObject.add(this);

        Assertions.assertTrue(contextObject == null || contextObject == this.context);
        contextObject = this.context;
    }
}
