package pl.malek.automi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.malek.automi.entity.ReminderEntity;

@Repository
public interface PasswordReminderRepository extends JpaRepository<ReminderEntity, Long> {

    void deleteByEmail(String email);

    ReminderEntity findByUuid(String uuid);

}
