package server.Model;

        import com.fasterxml.jackson.annotation.JsonFormat;

        import javax.xml.bind.annotation.XmlAccessType;
        import javax.xml.bind.annotation.XmlAccessorType;
        import javax.xml.bind.annotation.XmlRootElement;
        import java.time.LocalDate;

/**
 * Created by IgorPc on 11/20/2018.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Payment {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate localDate;
    private double sum;
    private String description;

    public Payment() {
    }

    public Payment(LocalDate localDate, double sum, String description) {
        this.localDate = localDate;
        this.sum = sum;
        this.description = description;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
