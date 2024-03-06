package sample.cafekiosk.spring.api.service.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.spring.client.mail.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Mock 객체를 사용할꺼야 라고 알림.
 */
@ExtendWith(MockitoExtension.class)
class MailServiceTest {

//    @Spy
    @Mock
    private MailSendClient mailSendClient;
    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    // @Mock 객체들에 대한 의존성을 주입 받는다.
    // DI개념
    @InjectMocks
    private MailService mailService;

    @Test
    @DisplayName("메일 전송 테스트")
    void sendMail() {
        // given
//        MailSendClient mailSendClient = mock(MailSendClient.class);
//        MailSendHistoryRepository mailSendHistoryRepository = mock(MailSendHistoryRepository.class);
//        MailService mailService = new MailService(mailSendClient, mailSendHistoryRepository);

        /**
         * Mock 사용시 문법
         */
//        when(mailSendClient.sendMail(any(String.class), any(String.class), any(String.class), any(String.class)))
//                .thenReturn(true);
        /**
         * BDDMockito
         */
        given(mailSendClient.sendMail(any(String.class), any(String.class), any(String.class), any(String.class)))
                .willReturn(true);
        /**
         * Spy 사용시 문법
         */
//        doReturn(true)
//                .when(mailSendClient)
//                .sendMail(any(String.class), any(String.class), any(String.class), any(String.class));




        // when
        boolean result = mailService.sendMail("", "", "", "");


        // then
        /**
         * Mockito 사용시 save 하면 null이 반환되므로, save에 대한검증임.
         */
        verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
        assertThat(result).isTrue();
    }

}