package solubris.fundingcircle.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by eeo2 on 16/09/2014.
 */
@Component
public class MailService {
//    @Autowired
    private MailSender mailSender;

    public void sendMail(String msg) {
        SimpleMailMessage message = new SimpleMailMessage();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String today = format.format(new Date());

        message.setFrom("noreply@solubris.com");
        message.setTo("tim@solubris.com");
        message.setSubject("newly sold loan parts for " + today);
        message.setText(msg);
        mailSender.send(message);
    }
}
