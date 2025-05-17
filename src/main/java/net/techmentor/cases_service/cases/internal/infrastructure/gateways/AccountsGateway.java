package net.techmentor.cases_service.cases.internal.infrastructure.gateways;

import net.techmentor.cases_service.accounts.shared.AccountDTO;
import net.techmentor.cases_service.accounts.shared.IAccountsAPI;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class AccountsGateway {
    private final IAccountsAPI accountsAPI;

    public AccountsGateway(IAccountsAPI accountsAPI) {
        this.accountsAPI = accountsAPI;
    }

    public CompletableFuture<List<AccountDTO>> getAccountsByIds(List<UUID> idsList) {
        return accountsAPI.getAccountsByIds(idsList);
    }
}
