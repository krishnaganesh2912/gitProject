package com.cg.hims.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Policy {
    @Id
    @NotNull
    @Min(1)
    private int policyId;
    @NotEmpty(message = "policyEffectiveDate is required")
    private String policyEffectiveDate;
    @NotEmpty(message = "policyEndDate is required")
    private String policyEndDate;
    @Min(1)
    private int policyTerm;
    @NotNull
    @NotEmpty(message = "policyStatus is required")
    private String policyStatus;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="quote_id")
    private Quote quote;
    

    @ManyToOne
    @JoinColumn(name="agentId")
    private Agent agent;
    
    public Policy() {
        super();
    }

 public int getPolicyId() {
        return policyId;
    }

public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

public String getPolicyEffectiveDate() {
        return policyEffectiveDate;
    }

public void setPolicyEffectiveDate(String policyEffectiveDate) {
        this.policyEffectiveDate = policyEffectiveDate;
    }

public String getPolicyEndDate() {
        return policyEndDate;
    }

public void setPolicyEndDate(String policyEndDate) {
        this.policyEndDate = policyEndDate;
    }

public int getPolicyTerm() {
        return policyTerm;
    }

public void setPolicyTerm(int policyTerm) {
        this.policyTerm = policyTerm;
    }

public String getPolicyStatus() {
        return policyStatus;
    }

public void setPolicyStatus(String policyStatus) {
        this.policyStatus = policyStatus;
    }

 public Quote getQuote() {
        return quote;
    }

public void setQuote(Quote quote) {
        this.quote = quote;
    }

public Agent getAgent() {
        return agent;
    }

public void setAgent(Agent agent) {
        this.agent = agent;
    }
 
@Override
    public String toString() {
        return "Policy [policyId=" + policyId + ", policyEffectiveDate=" + policyEffectiveDate + ", policyEndDate="
                + policyEndDate + ", policyTerm=" + policyTerm + ", policyStatus=" + policyStatus + ", quote=" + quote
                + ", agent=" + agent + "]";
    }
    
}