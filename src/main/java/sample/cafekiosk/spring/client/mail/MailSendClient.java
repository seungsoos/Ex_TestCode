package sample.cafekiosk.spring.client.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class MailSendClient {

    public boolean sendMail(String fromEmail, String email, String title, String content) {
        log.info("메일 전송");

        throw new IllegalArgumentException("메일 전송");
    }

    public void  a() {
        log.info("a");
    }
    public void  b() {
        log.info("b");
    }
    public void c() {
        log.info("c");
    }
}
