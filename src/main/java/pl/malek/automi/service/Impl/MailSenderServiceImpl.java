package pl.malek.automi.service.Impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.malek.automi.dto.Mail;
import pl.malek.automi.dto.MailStatus;
import pl.malek.automi.entity.ReminderEntity;
import pl.malek.automi.service.MailSenderService;
import pl.malek.automi.service.ReminderService;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {

    private JavaMailSender javaMailSender;

    private ReminderService reminderService;

    @Override
    public boolean send(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("automi.info@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);
            log.info("Sending email to new user: " + to);
            return true;
        } catch (Exception e) {
            log.warn("Error while sending email to new user: " + to);
            return false;
        }
    }

    @Override
    public MailStatus changePassword(Mail mail) {

        UUID uuid = UUID.randomUUID();
        String url = String.format("http://localhost:4200/change-password/%s", uuid);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getTo());
        message.setText(String.format("Here is your link to changing password site: %s", url));
        message.setSubject("AutoMI - Change Password");

        javaMailSender.send(message);

        reminderService.save(ReminderEntity.builder().email(mail.getTo()).uuid(uuid.toString()).build());

        return MailStatus.builder().build();
    }


}
