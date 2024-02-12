package sample.cafekiosk.unit.beverages;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AmericanoTest {

    @Test
    void getName() {
        Americano americano = new Americano();
        assertEquals(americano.getName(), "아메리카노"); // Junit
        Assertions.assertThat(americano.getName()).isEqualTo("아메리카노"); // assertj 라이브러리
    }

    @Test
    void getPrice() {
        Americano americano = new Americano();
        Assertions.assertThat(americano.getPrice()).isEqualTo(4000);
    }
}