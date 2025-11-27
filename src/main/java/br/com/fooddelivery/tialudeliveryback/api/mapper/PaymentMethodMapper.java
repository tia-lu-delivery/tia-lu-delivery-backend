package br.com.fooddelivery.tialudeliveryback.api.mapper;

import br.com.fooddelivery.tialudeliveryback.api.dto.PaymentMethodInputDTO;
import br.com.fooddelivery.tialudeliveryback.api.dto.PaymentMethodOutputDTO;
import br.com.fooddelivery.tialudeliveryback.api.dto.UserWalletOutputDTO;
import br.com.fooddelivery.tialudeliveryback.core.domain.PaymentMethod;
import br.com.fooddelivery.tialudeliveryback.core.domain.UserWallet;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentMethodMapper {

    public static PaymentMethod toDomain(PaymentMethodInputDTO inputDTO) {
        // In a real application, you would handle masking/encryption of card details here
        String maskedCardNumber = maskCardNumber(inputDTO.getCardNumber());
        return new PaymentMethod(
                inputDTO.getUserId(),
                inputDTO.getType(),
                maskedCardNumber,
                inputDTO.getExpiryDate(),
                inputDTO.getHolderName()
        );
    }

    public static PaymentMethodOutputDTO toOutputDTO(PaymentMethod domain) {
        return new PaymentMethodOutputDTO(
                domain.getId(),
                domain.getType(),
                domain.getCardNumber(), // This should already be masked or last four digits
                domain.getExpiryDate(),
                domain.getHolderName()
        );
    }

    public static List<PaymentMethodOutputDTO> toOutputDTOList(List<PaymentMethod> domainList) {
        return domainList.stream()
                .map(PaymentMethodMapper::toOutputDTO)
                .collect(Collectors.toList());
    }

    public static UserWalletOutputDTO toUserWalletOutputDTO(UserWallet userWallet) {
        List<PaymentMethodOutputDTO> paymentMethodOutputDTOS = userWallet.getPaymentMethods().stream()
                .map(PaymentMethodMapper::toOutputDTO)
                .collect(Collectors.toList());
        return new UserWalletOutputDTO(userWallet.getUserId(), paymentMethodOutputDTOS);
    }

    private static String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) {
            return cardNumber;
        }
        return "XXXX-XXXX-XXXX-" + cardNumber.substring(cardNumber.length() - 4);
    }
}
