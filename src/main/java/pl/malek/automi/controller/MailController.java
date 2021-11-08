package pl.malek.automi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.malek.automi.dto.Mail;
import pl.malek.automi.dto.MailStatus;
import pl.malek.automi.dto.NewPassword;
import pl.malek.automi.service.MailSenderService;
import pl.malek.automi.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/email")
public class MailController {

    private MailSenderService mailSenderService;

    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<MailStatus> sendMailWithLinkToChangePassword(@RequestBody Mail mail) {
        return ResponseEntity.status(HttpStatus.OK).body(mailSenderService.changePassword(mail));
    }

    @PostMapping("/{uuid}")
    public ResponseEntity<Boolean> changePassword(@RequestBody NewPassword newPassword, @PathVariable String uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.changePassword(uuid, newPassword.getNewPassword()));
    }

}
