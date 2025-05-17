package net.techmentor.cases_service.accounts.shared;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IAccountsAPI {
    CompletableFuture<InvitationResponse> getInvitationByMobileNumber(String mobileNumber);

    CompletableFuture<AccountDTO> getById(UUID id);

    CompletableFuture<List<AccountDTO>> getAccountsByIds(List<UUID> idsList);
}
