package sample.cafekiosk.spring.domain.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
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
        Product build1 = Product.builder()
                .productNumber("001")
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("아메리카노")
                .price(4000)
                .build();
        Product build2 = Product.builder()
                .productNumber("002")
                .type(BAKERY)
                .sellingStatus(SELLING)
                .name("소금빵")
                .price(5000)
                .build();
        Product build3 = Product.builder()
                .productNumber("003")
                .type(HANDMADE)
                .sellingStatus(STOP_SELLING)
                .name("카페라떼")
                .price(4500)
                .build();

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
        Product build1 = Product.builder()
                .productNumber("001")
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("아메리카노")
                .price(4000)
                .build();
        Product build2 = Product.builder()
                .productNumber("002")
                .type(BAKERY)
                .sellingStatus(SELLING)
                .name("소금빵")
                .price(5000)
                .build();
        Product build3 = Product.builder()
                .productNumber("003")
                .type(HANDMADE)
                .sellingStatus(STOP_SELLING)
                .name("카페라떼")
                .price(4500)
                .build();

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




}