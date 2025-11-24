package br.com.fooddelivery.tialudeliveryback.controller.user;

import br.com.fooddelivery.tialudeliveryback.service.user.UserInactivationService;
import br.com.fooddelivery.tialudeliveryback.dto.user.UserInactivationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserMeController {

    private final UserInactivationService userInactivationService;

    @PutMapping("/me")
    public ResponseEntity<UserInactivationResponse> inactivateAuthenticatedUser() {

        Long userId = extractUserIdFromToken();

        UserInactivationResponse response = userInactivationService.inactivate(userId);

        return ResponseEntity.ok(response); // 200 OK
    }

    private Long extractUserIdFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Troque para o principal correto quando soubermos qual é:
        var principal = (UserPrincipal) authentication.getPrincipal();
        return principal.getId();
    }
}
