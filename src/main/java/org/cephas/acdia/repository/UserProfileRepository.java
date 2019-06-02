package org.cephas.acdia.repository;

import org.cephas.acdia.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by admin on 20-05-19.
 */
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    @Override
    Optional<UserProfile> findById(Long id);

    List<UserProfile> findAllByUserId(long userId);
}
