package br.com.fooddelivery.tialudeliveryback.service.user;

import br.com.fooddelivery.tialudeliveryback.domain.user.User;
import br.com.fooddelivery.tialudeliveryback.domain.user.UserRepository;
import br.com.fooddelivery.tialudeliveryback.domain.user.exception.AccountAlreadyInactiveException;
import br.com.fooddelivery.tialudeliveryback.dto.user.UserInactivationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInactivationService {

    private final UserRepository userRepository;

    public UserInactivationResponse inactivate(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.isInactive()) {
            throw new AccountAlreadyInactiveException();
        }

        user.setInactive(true);
        userRepository.save(user);

        return new UserInactivationResponse(
                user.getId(),
                "INACTIVE",
                user.getUpdatedAt()
        );
    }
}
