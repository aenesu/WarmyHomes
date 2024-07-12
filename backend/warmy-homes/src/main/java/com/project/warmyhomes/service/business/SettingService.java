package com.project.warmyhomes.service.business;

import com.project.warmyhomes.repository.business.*;
import com.project.warmyhomes.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SettingService {
    // User repository
    private final UserRepository userRepository;

    // Business repositories
    private final AdvertRepository advertRepository;
    private final AdvertTypeRepository advertTypeRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryPropertyKeyRepository categoryPropertyKeyRepository;
    private final CategoryPropertyValueRepository categoryPropertyValueRepository;
    private final ContactRepository contactRepository;
    private final FavoriteRepository favoriteRepository;
    private final ImageRepository imageRepository;
    private final LogRepository logRepository;
    private final TourRequestRepository tourRequestRepository;

    /**
     * Reset the database by deleting all entries that are not built-in.
     * This method is transactional, meaning all deletions will be rolled back if any fail.
     */
    @Transactional
    public void resetDatabase() {
        userRepository.deleteByBuiltInFalse();
        advertRepository.deleteByBuiltInFalse();
        advertTypeRepository.deleteByBuiltInFalse();
        categoryRepository.deleteByBuiltInFalse();
        categoryPropertyKeyRepository.deleteByBuiltInFalse();
        categoryPropertyValueRepository.deleteAll();
        contactRepository.deleteAll();
        favoriteRepository.deleteAll();
        logRepository.deleteAll();
        tourRequestRepository.deleteAll();
        imageRepository.deleteAll();
    }
}
