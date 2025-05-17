package net.techmentor.cases_service.cases.internal.infrastructure.queryhandlers;

import net.techmentor.cases_service.accounts.shared.AccountDTO;
import net.techmentor.cases_service.cases.internal.application.queries.GetCase.GetCaseQuery;
import net.techmentor.cases_service.cases.internal.application.queries.GetCase.GetCaseResponse;
import net.techmentor.cases_service.cases.internal.application.queries.GetCase.IGetCaseHandler;
import net.techmentor.cases_service.cases.internal.infrastructure.gateways.AccountsGateway;
import net.techmentor.cases_service.cases.internal.infrastructure.repositories.ReadCaseRepo;
import net.techmentor.cases_service.shared.abstractions.QueryHandler;
import net.techmentor.cases_service.shared.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class GetCaseHandler implements QueryHandler<GetCaseQuery, GetCaseResponse>, IGetCaseHandler {
    private final ReadCaseRepo caseRepo;
    private final GetCaseMapper getCaseMapper;
    private final AccountsGateway accountsGateway;

    public GetCaseHandler(
            ReadCaseRepo caseRepo,
            GetCaseMapper getCaseMapper, AccountsGateway accountsGateway
    ) {
        this.caseRepo = caseRepo;
        this.getCaseMapper = getCaseMapper;
        this.accountsGateway = accountsGateway;
    }

    @Override
    public CompletableFuture<GetCaseResponse> handle(GetCaseQuery query) {
        return CompletableFuture.supplyAsync(() -> {
            var case_ = caseRepo.getByCode(query.caseCode()).join();
            if (case_ == null) {
                throw new NotFoundException(String.format("Case with code %s is not found", query.caseCode()));
            }

            var contributions = caseRepo.getContributionsByCaseCode(case_.code()).join();

            // get the contributors details only if the account has full access
            if (query.accessTokenPayload().hasFullAccess()) {

                List<UUID> contributorsIds = contributions
                        .stream()
                        .map(contribution -> UUID.fromString(contribution.contributorId()))
                        .toList();

                List<AccountDTO> contributors = accountsGateway.getAccountsByIds(contributorsIds).join();

                var caseDetails = getCaseMapper.toCaseDetails(case_, contributions, contributors);
                return new GetCaseResponse(caseDetails);
            }

            var caseDetails = getCaseMapper.toCaseDetails(case_, contributions, null);
            return new GetCaseResponse(caseDetails);
        });
    }
}
