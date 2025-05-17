package net.techmentor.cases_service.cases.internal.application.queries.GetAllCases;

import net.techmentor.cases_service.shared.abstractions.Query;

public record GetAllCasesQuery(
        Integer code,
        String tag,
        String content,
        int offset,
        int limit
) implements Query {

}
