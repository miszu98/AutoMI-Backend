package pl.malek.automi.service;

import pl.malek.automi.dto.Mail;
import pl.malek.automi.dto.MailStatus;

public interface MailSenderService {

    boolean send(String to, String subject, String text);

    MailStatus changePassword(Mail mail);

}
