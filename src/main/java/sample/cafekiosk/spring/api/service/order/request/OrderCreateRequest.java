package sample.cafekiosk.spring.api.service.order.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class OrderCreateRequest {

    private List<String> productNumbers;
}
