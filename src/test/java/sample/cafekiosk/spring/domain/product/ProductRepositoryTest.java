package sample.cafekiosk.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.*;
import static sample.cafekiosk.spring.domain.product.ProductType.BAKERY;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;


@ActiveProfiles("test")
/**
 * 스프링 서버를 띄워서 통합테스트
 */
//@SpringBootTest
/**
 * 스프링 서버를 띄워서 테스트 하지만,
 * JPA 관련 빈만 등록하고 테스트 SpringBootTest 보다는 되게 가벼움
 */
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;


    @DisplayName("원하는 판매상태를 가진 상품들을 조회한다.")
    @Test
    void findAllBySellingStatusIn() {
        // given
        Product build1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        Product build2 = createProduct("002", BAKERY, SELLING, "소금빵", 5000);
        Product build3 = createProduct("003", HANDMADE, STOP_SELLING, "카페라떼", 4500);

        productRepository.saveAll(List.of(build1, build2, build3));

        // when
        List<Product> products = productRepository.findAllBySellingStatusIn(List.of(SELLING, HOLD));

        //then
        assertThat(products).hasSize(2)
                .extracting("productNumber", "name", "sellingStatus")
                .containsExactlyInAnyOrder(
                        tuple("001", "아메리카노", SELLING),
                        tuple("002", "소금빵", SELLING)
                );

    }

    @Test
    @DisplayName("상품번호 리스트로 상품들을 조회한다.")
    void findAllByProductNumberIn() {
        // given
        Product build1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        Product build2 = createProduct("002", BAKERY, SELLING, "소금빵", 5000);
        Product build3 = createProduct("003", HANDMADE, STOP_SELLING, "카페라떼", 4500);

        productRepository.saveAll(List.of(build1, build2, build3));

        // when
        List<Product> products = productRepository.findAllByProductNumberIn(List.of("001", "002"));

        //then
        assertThat(products).hasSize(2)
                .extracting("productNumber", "name", "sellingStatus")
                .containsExactlyInAnyOrder(
                        tuple("001", "아메리카노", SELLING),
                        tuple("002", "소금빵", SELLING)
                );
    }

    @Test
    @DisplayName("가장 마지막에 저장한 상품번호를 읽어온다.")
    void findLatestProductNumber() {
        // given
        String targetProductNumber = "003";
        Product build1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        Product build2 = createProduct("002", BAKERY, SELLING, "소금빵", 5000);
        Product build3 = createProduct(targetProductNumber, HANDMADE, STOP_SELLING, "카페라떼", 4500);

        productRepository.saveAll(List.of(build1, build2, build3));

        // when
        String latestProductNumber = productRepository.findLatestProductNumber();

        //then
        assertThat(latestProductNumber).isEqualTo(targetProductNumber);
    }

    @Test
    @DisplayName("가장 마지막에 저장한 상품번호를 읽어올때 상품이 하나도 없는경우 null을 반환한다")
    void findLatestProductNumberWhenProductIsEmpty() {
        // given
        // when
        String latestProductNumber = productRepository.findLatestProductNumber();
        //then
        assertThat(latestProductNumber).isEqualTo(null);
    }

    private Product createProduct(String productNumber, ProductType type, ProductSellingStatus status, String name, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .sellingStatus(status)
                .name(name)
                .price(price)
                .build();
    }


}