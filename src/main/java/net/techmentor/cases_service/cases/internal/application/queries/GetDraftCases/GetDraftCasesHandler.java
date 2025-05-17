package net.techmentor.cases_service.cases.internal.application.queries.GetDraftCases;

import net.techmentor.cases_service.cases.internal.infrastructure.repositories.ReadCaseRepo;
import net.techmentor.cases_service.shared.abstractions.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class GetDraftCasesHandler implements QueryHandler<GetDraftCases, GetDraftCasesResponse> {
    private final ReadCaseRepo caseRepo;

    public GetDraftCasesHandler(ReadCaseRepo caseRepo) {
        this.caseRepo = caseRepo;
    }

    @Override
    public CompletableFuture<GetDraftCasesResponse> handle(GetDraftCases query) {
        List<GetDraftCasesResponse.DraftCase> draftCases = caseRepo.getDraftCases().join()
                .stream()
                .map(it -> new GetDraftCasesResponse.DraftCase(
                        it.code(),
                        it.title(),
                        it.description(),
                        it.goal(),
                        it.creationDate(),
                        it.lastUpdated(),
                        it.documents()
                ))
                .collect(Collectors.toList());

        return CompletableFuture.completedFuture(new GetDraftCasesResponse(draftCases));
    }
}
