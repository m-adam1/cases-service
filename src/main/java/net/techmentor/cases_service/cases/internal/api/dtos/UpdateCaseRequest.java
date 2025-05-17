package net.techmentor.cases_service.cases.internal.api.dtos;

import net.techmentor.cases_service.shared.abstractions.Request;

import java.util.List;

public record UpdateCaseRequest(String title, String description, int goal, boolean acceptZakat,
                                List<String> documents) implements Request {

}
