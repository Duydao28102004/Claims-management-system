package System.Customer;
import System.InsuranceCard;
import System.Claim;
import java.util.List;

public class Dependent extends Customer {
    private PolicyHolder policyHolder;
    public Dependent() {
        super();
        policyHolder = null;
    }

    public Dependent(String id, String fullName, InsuranceCard insuranceCard, List<Claim> claims, PolicyHolder policyHolder) {
        super(id, fullName, insuranceCard, claims);
        this.policyHolder = policyHolder;
    }

    public PolicyHolder getPolicyHolder() {
        return policyHolder;
    }

    public void setPolicyHolder(PolicyHolder policyHolder) {
        this.policyHolder = policyHolder;
    }
}
