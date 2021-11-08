package pl.malek.automi.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.malek.automi.entity.ReminderEntity;
import pl.malek.automi.repository.PasswordReminderRepository;
import pl.malek.automi.service.ReminderService;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class ReminderServiceImpl implements ReminderService {

    private PasswordReminderRepository passwordReminderRepository;

    @Override
    @Transactional
    public void deleteReminderByEmail(String email) {
        passwordReminderRepository.deleteByEmail(email);
    }

    @Override
    public ReminderEntity save(ReminderEntity reminderEntity) {
        return passwordReminderRepository.save(reminderEntity);
    }

    @Override
    public ReminderEntity findByUuid(String uuid) {
        return passwordReminderRepository.findByUuid(uuid);
    }


}
