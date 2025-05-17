package net.techmentor.cases_service.accounts.shared;

import java.util.UUID;

public record InvitationResponse(String invitedMobileNumber, UUID inviterId) {
}
