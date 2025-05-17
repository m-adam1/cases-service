package net.techmentor.cases_service.accounts.shared;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@FeignClient(name = "accounts-service", url = "${accounts-service.url}")
public interface AccountsFeignClient extends IAccountsAPI {
    
    @Override
    @GetMapping("/v1/accounts/invitations/{mobileNumber}")
    CompletableFuture<InvitationResponse> getInvitationByMobileNumber(@PathVariable("mobileNumber") String mobileNumber);

    @Override
    @GetMapping("/v1/accounts/{id}")
    CompletableFuture<AccountDTO> getById(@PathVariable("id") UUID id);

    @Override
    @GetMapping("/v1/accounts")
    CompletableFuture<List<AccountDTO>> getAccountsByIds(@RequestParam("ids") List<UUID> idsList);
} 
