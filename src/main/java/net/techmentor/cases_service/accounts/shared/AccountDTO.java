package net.techmentor.cases_service.accounts.shared;

import java.util.List;

public record AccountDTO(String id,
                         String mobileNumber,
                         String fullName,
                         String photoUrl,
                         List<String> devicesTokens) {
}
