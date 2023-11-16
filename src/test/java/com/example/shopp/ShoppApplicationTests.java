package com.example.shopp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class ShoppApplicationTests {

    Calculator underTest = new Calculator();

    @Test
    void contextLoads() {
    }

    @Test
    void itShouldAddNumbers() {
        int one = 30;
        int two = 20;

        int result = underTest.add(one, two);

        int expected = 50;
        assertThat(result).isEqualTo(expected);
    }
}

class Calculator {
    int add(int n, int m) {
        return n + m;
    }
}
