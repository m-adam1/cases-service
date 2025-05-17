package net.techmentor.cases_service.cases.internal.application.commands.CreateCase;

import net.techmentor.cases_service.shared.abstractions.Command;

import java.util.List;

public record CreateCase(String title, String description, int goal, boolean publish, boolean acceptZakat,
                         List<String> documents) implements Command {
}
