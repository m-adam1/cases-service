package net.techmentor.cases_service.cases.internal.infrastructure.queryhandlers;

import net.techmentor.cases_service.cases.internal.application.queries.GetAllCases.GetAllCasesQuery;
import net.techmentor.cases_service.cases.internal.application.queries.GetAllCases.GetCasesQueryResult;
import net.techmentor.cases_service.cases.internal.application.queries.GetAllCases.IGetAllCasesHandler;
import net.techmentor.cases_service.cases.internal.infrastructure.db.CaseEntity;
import net.techmentor.cases_service.cases.internal.infrastructure.repositories.ReadCaseRepo;
import net.techmentor.cases_service.shared.abstractions.QueryHandler;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import static net.techmentor.cases_service.cases.internal.application.queries.GetAllCases.GetCasesQueryResult.Case;

@Service
public class GetAllCasesHandler implements QueryHandler<GetAllCasesQuery, GetCasesQueryResult>, IGetAllCasesHandler {
    private final ReadCaseRepo caseRepo;

    public GetAllCasesHandler(ReadCaseRepo caseRepo) {
        this.caseRepo = caseRepo;
    }

    @Override
    public CompletableFuture<GetCasesQueryResult> handle(GetAllCasesQuery query) {
        return CompletableFuture.supplyAsync(() -> {

            Supplier<Bson> filter = filtersFrom(query);

            List<Case> cases = caseRepo.search(query.offset(), query.limit(), filter)
                    .join()
                    .stream()
                    .map(CaseEntity::toQueryResult)
                    .toList();

            int casesCount = caseRepo.getCasesCount(filter).join();

            return new GetCasesQueryResult(cases, casesCount);
        });
    }

    private static Supplier<Bson> filtersFrom(GetAllCasesQuery query) {
        return () -> {
            List<Bson> conditions = new ArrayList<>();

            if (query.code() != null) {
                conditions.add(Filters.eq("code", query.code()));
            }

            if (query.tag() != null) {
                conditions.add(Filters.in("tags", query.tag()));
            }

            if (query.content() != null) {
                conditions.add(
                        Filters.or(
                                Filters.regex("title", Pattern.compile(".*" + query.content() + ".*")),
                                Filters.regex("description", Pattern.compile(".*" + query.content() + ".*"))
                        )
                );
            }
            return conditions.isEmpty() ? new Document() : Filters.and(conditions);
        };
    }

}
