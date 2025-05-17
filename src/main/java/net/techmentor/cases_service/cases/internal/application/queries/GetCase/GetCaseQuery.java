package net.techmentor.cases_service.cases.internal.application.queries.GetCase;

import net.techmentor.cases_service.shared.abstractions.Query;
import net.techmentor.cases_service.shared.auth.AccessTokenPayload;

public record GetCaseQuery(int caseCode, AccessTokenPayload accessTokenPayload) implements Query {
}
