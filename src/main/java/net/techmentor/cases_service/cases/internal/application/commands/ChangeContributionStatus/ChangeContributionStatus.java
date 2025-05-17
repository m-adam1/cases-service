package net.techmentor.cases_service.cases.internal.application.commands.ChangeContributionStatus;

import net.techmentor.cases_service.shared.abstractions.Command;

import java.util.UUID;

public record ChangeContributionStatus(UUID contributionId, boolean isPay) implements Command {
}
