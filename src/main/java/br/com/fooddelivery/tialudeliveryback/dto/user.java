package br.com.fooddelivery.tialudeliveryback.dto.user;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInactivationResponse {

    private Long id;
    private String status;
    private OffsetDateTime inactivatedAt;
}
