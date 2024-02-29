package sample.cafekiosk.spring.api.controller.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sample.cafekiosk.spring.api.controller.product.request.ProductCreateRequest;
import sample.cafekiosk.spring.api.service.product.ProductService;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    @DisplayName("신규 상품을 등록한다.")
    void createProduct() throws Exception {

        // given
        ProductCreateRequest productCreateRequest = ProductCreateRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("아메리카노")
                .price(4000)
                .build();

        // when-then
        mockMvc.perform(post("/api/v1/products/new")
                        .content(objectMapper.writeValueAsString(productCreateRequest))
                        .contentType(APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())               // 로그 확인용
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    @DisplayName("신규 상품을 등록할때 상품 타입은 필수 값이다.")
    void createProductWithoutType() throws Exception {

        // given
        ProductCreateRequest productCreateRequest = ProductCreateRequest.builder()
                .sellingStatus(SELLING)
                .name("아메리카노")
                .price(4000)
                .build();

        // when-then
        mockMvc.perform(post("/api/v1/products/new")
                        .content(objectMapper.writeValueAsString(productCreateRequest))
                        .contentType(APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())               // 로그 확인용
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("상품 타입은 필수입니다."))
        ;
    }
    @Test
    @DisplayName("신규 상품을 등록할때 상품 판매상태는 필수 값이다.")
    void createProductWithoutStatus() throws Exception {

        // given
        ProductCreateRequest productCreateRequest = ProductCreateRequest.builder()
                .type(HANDMADE)
                .name("아메리카노")
                .price(4000)
                .build();

        // when-then
        mockMvc.perform(post("/api/v1/products/new")
                        .content(objectMapper.writeValueAsString(productCreateRequest))
                        .contentType(APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())               // 로그 확인용
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("상품 판매 상태는 필수입니다."))
        ;
    }

    @Test
    @DisplayName("신규 상품을 등록할때 상품 이름은 필수 값이다.")
    void createProductWithoutName() throws Exception {

        // given
        ProductCreateRequest productCreateRequest = ProductCreateRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .price(4000)
                .build();

        // when-then
        mockMvc.perform(post("/api/v1/products/new")
                        .content(objectMapper.writeValueAsString(productCreateRequest))
                        .contentType(APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())               // 로그 확인용
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("상품 이름은 필수입니다."))
        ;
    }

    @Test
    @DisplayName("신규 상품을 등록할때 상품 가격은 필수 값이다.")
    void createProductWithoutPrice() throws Exception {

        // given
        ProductCreateRequest productCreateRequest = ProductCreateRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("아메리카노")
                .build();

        // when-then
        mockMvc.perform(post("/api/v1/products/new")
                        .content(objectMapper.writeValueAsString(productCreateRequest))
                        .contentType(APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())               // 로그 확인용
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("상품 가격은 양수여야 합니다."))
        ;
    }



}