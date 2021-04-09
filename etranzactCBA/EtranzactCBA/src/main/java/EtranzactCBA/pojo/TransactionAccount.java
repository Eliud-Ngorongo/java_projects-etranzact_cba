package EtranzactCBA.pojo;

import io.micronaut.core.annotation.Introspected;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


//@Embeddable
@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Introspected
public class TransactionAccount /*implements AccountDetails*/ {

    //@Enumerated(EnumType.STRING)
    private TransactionChannel transactionChannel;

    //@Enumerated(EnumType.STRING)
    private CountryCode countryCode;

    private BankAccount bankAccount;
    private MobileMoneyAccount mobileMoneyAccount;
    private CardAccount cardAccount;
    private EWalletAccount e_WalletAccount;
    //private OfflineAccount offlineAccount;

    //@Transient
    private String customerNo;

    @NotNull
    @NotBlank
    @Setter
    private String customerName;

    //@Transient
    private String customerEmail;

    //@Transient
    private String inHouseAccountant;

    //@Transient
    private String central_trans_id;

    public BankAccount getBankAccount() {
        if (bankAccount == null) {
            bankAccount = new BankAccount();
        }
        return bankAccount;
    }

    public MobileMoneyAccount getMobileMoneyAccount() {
        if (mobileMoneyAccount == null) {
            mobileMoneyAccount = new MobileMoneyAccount();
        }
        return mobileMoneyAccount;
    }

    public CardAccount getCardAccount() {
        if (cardAccount == null) {
            cardAccount = new CardAccount();
        }
        return cardAccount;
    }

    public EWalletAccount gete_WalletAccount() {
        if (e_WalletAccount == null) {
            e_WalletAccount = new EWalletAccount();
        }
        return e_WalletAccount;
    }

    public void applyAliasSetting() {
        nestedSetCustomerName();
        nestedSetCustomerNo();
    }

    private void nestedSetCustomerName() {
        if (this.customerName != null) {
            if (TransactionChannel.MOBILE_MONEY == transactionChannel) {
                getMobileMoneyAccount().setMobileMoneyName(this.customerName);
            } else if (TransactionChannel.CARD == transactionChannel) {
                getCardAccount().setCardAccountName(this.customerName);
            } else if (TransactionChannel.BANK == transactionChannel) {
                getBankAccount().setAccountName(this.customerName);
            }
        }
    }

    private void nestedSetCustomerNo() {
        if (this.customerNo != null) {
            if (TransactionChannel.MOBILE_MONEY == transactionChannel) {
                getMobileMoneyAccount().setAccountNumber(this.customerNo);
            } else if (TransactionChannel.CARD == transactionChannel) {
                getCardAccount().setCardAccountNumber(this.customerNo);
            } else if (TransactionChannel.BANK == transactionChannel) {
                getBankAccount().setAccountNumber(this.customerNo);
            }
        }
    }
}
