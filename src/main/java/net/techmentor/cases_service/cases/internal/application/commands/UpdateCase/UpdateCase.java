package net.techmentor.cases_service.cases.internal.application.commands.UpdateCase;

import net.techmentor.cases_service.shared.abstractions.Command;

import java.util.List;

public record UpdateCase(int caseCode, String title, String description, int goal, boolean acceptZakat,
                         List<String> documents) implements Command {

}
