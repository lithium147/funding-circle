package solubris.fundingcircle.selenium.control;

import java.util.Date;

/**
 * Created by eeo2 on 18/09/2014.
 */
public class LoanPart {
    private final long id;
    private final String title;
    private final long loanId;
    private final String risk;
    private final double markUpPercent;
    private final double markUpAmount;
    private final double salePrice;
    private final double buyerRate;
    private final Date dateSold;
    private final String buyer;

    public LoanPart(long id, String title, long loanId, String risk, double markUpPercent, double markUpAmount, double salePrice, double buyerRate, Date dateSold, String buyer) {
        this.id = id;
        this.title = title;
        this.loanId = loanId;
        this.risk = risk;
        this.markUpPercent = markUpPercent;
        this.markUpAmount = markUpAmount;
        this.salePrice = salePrice;
        this.buyerRate = buyerRate;
        this.dateSold = dateSold;
        this.buyer = buyer;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getLoanId() {
        return loanId;
    }

    public String getRisk() {
        return risk;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public double getBuyerRate() {
        return buyerRate;
    }

    public Date getDateSold() {
        return dateSold;
    }

    public String getBuyer() {
        return buyer;
    }

    public double getMarkUpPercent() {
        return markUpPercent;
    }

    public double getMarkUpAmount() {
        return markUpAmount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoanPart{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", loanId=").append(loanId);
        sb.append(", risk='").append(risk).append('\'');
        sb.append(", markUpPercent=").append(markUpPercent);
        sb.append(", markUpAmount=").append(markUpAmount);
        sb.append(", salePrice=").append(salePrice);
        sb.append(", buyerRate=").append(buyerRate);
        sb.append(", dateSold=").append(dateSold);
        sb.append(", buyer='").append(buyer).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
