package springbook.learningtest.template;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CalcSumTest {

    Calculator calculator;
    String numFilepath;
    @BeforeEach
    public void setUp(){
        this.calculator = new Calculator();
        this.numFilepath = getClass().getResource("/numbers.txt").getPath();
    }
    @Test
    public void sumOfNumbers() throws IOException{
        Assertions.assertThat(calculator.calcSum(this.numFilepath)).isEqualTo(10);
    }


    @Test
    public void multiplyOfNumbers() throws IOException{
        Assertions.assertThat(calculator.calcMultiply(this.numFilepath)).isEqualTo(24);
    }


    @Test
    public void StringOfNumbers() throws IOException{
        Assertions.assertThat(calculator.calcString(this.numFilepath)).isEqualTo("1234");
    }


}
