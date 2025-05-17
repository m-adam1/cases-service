package net.techmentor.cases_service.cases.internal.domain.model.Contribution;

import net.techmentor.cases_service.cases.internal.domain.events.ContributionConfirmed;
import net.techmentor.cases_service.cases.internal.domain.events.ContributionPaid;
import net.techmentor.cases_service.cases.internal.domain.model.Case.CaseCode;
import net.techmentor.cases_service.shared.domain.model.AggregateRoot;
import net.techmentor.cases_service.shared.exceptions.BusinessRuleException;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class Contribution extends AggregateRoot<ContributionId> {
    private final UUID contributorId;
    private final CaseCode caseId;
    private ContributionStatus contributionStatus;
    private final MoneyValue moneyValue;
    private final Date contributionDate;

    public Contribution(
            ContributionId id,
            UUID contributorId,
            CaseCode caseCode,
            MoneyValue moneyValue,
            ContributionStatus contributionStatus,
            Date contributionDate
    ) {
        super(id);
        this.contributorId = contributorId;
        this.caseId = caseCode;
        this.moneyValue = moneyValue;
        this.contributionStatus = contributionStatus;
        this.contributionDate = contributionDate;
    }

    public static Contribution new_(
            UUID contributorId,
            int caseCode,
            int amount
    ) {
        return new Contribution(
                ContributionId.generate(),
                contributorId,
                new CaseCode(caseCode),
                MoneyValue.of(amount),
                ContributionStatus.PLEDGED,
                new Date()
        );
    }

    public static Contribution create(
            UUID contributorId,
            int caseCode,
            int amount,
            ContributionStatus contributionStatus,
            Date contributionDate
    ) {
        return create(
                UUID.randomUUID(),
                contributorId,
                caseCode,
                amount,
                contributionStatus,
                contributionDate
        );
    }

    public static Contribution create(
            UUID id,
            UUID contributorId,
            int caseCode,
            int amount,
            ContributionStatus contributionStatus,
            Date contributionDate
    ) {
        return new Contribution(
                new ContributionId(id),
                contributorId,
                new CaseCode(caseCode),
                MoneyValue.of(amount),
                contributionStatus,
                contributionDate != null ? contributionDate : new Date()
        );
    }

    public void pay() {
        if (contributionStatus.isNotPledged()) {
            throw new BusinessRuleException("The Contribution has been paid already");
        }
        contributionStatus = ContributionStatus.PAID;
        raiseEvent(ContributionPaid.from(this));
    }

    public void confirm() {
        if (contributionStatus.isConfirmed()) {
            throw new BusinessRuleException("The Contribution either not paid or still pledged");
        }
        contributionStatus = ContributionStatus.CONFIRMED;
        raiseEvent(ContributionConfirmed.from(this));
    }

    public String contributionId() {
        return getId().value().toString();
    }
}
