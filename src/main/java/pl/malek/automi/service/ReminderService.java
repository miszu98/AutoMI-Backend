package pl.malek.automi.service;

import pl.malek.automi.entity.ReminderEntity;

public interface ReminderService {

    void deleteReminderByEmail(String email);

    ReminderEntity save(ReminderEntity reminderEntity);

    ReminderEntity findByUuid(String uuid);

}
