package sample.cafekiosk.unit;

import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverages.Americano;

class CafeKioskTest  {


    /**
     * 수동테스트
     * 올바르지 않은 테스트 코드 케이스
     */
    @Test
    void add() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        System.out.println(">>> 담긴 음료 수 : " + cafeKiosk.getBeverages().size());
        System.out.println(">>> 담긴 음료 이름 : " + cafeKiosk.getBeverages().get(0).getName());
    }
}