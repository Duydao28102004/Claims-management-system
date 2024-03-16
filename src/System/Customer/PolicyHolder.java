package System.Customer;
import System.InsuranceCard;
import System.Claim;
import java.util.List;

public class PolicyHolder extends Customer {
    private List<Dependent> dependents;

    public PolicyHolder(String id, String fullName, InsuranceCard insuranceCard, List<Claim> claims, List<Dependent> dependents) {
        super(id, fullName, insuranceCard, claims);
        this.dependents = dependents;
    }

    public List<Dependent> getDependents() {
        return dependents;
    }

    public void setDependents(List<Dependent> dependents) {
        this.dependents = dependents;
    }
}
