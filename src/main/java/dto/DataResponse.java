package dto;

import dto.structure.ErrorStructure;

import java.util.Date;
import java.util.Map;

public class DataResponse {
    private Boolean success;
    private Boolean historical;
    private Date date;
    private Long timestamp;
    private String base;
    private Map<String, Double> rates;
    private ErrorStructure error;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getHistorical() {
        return historical;
    }

    public void setHistorical(Boolean historical) {
        this.historical = historical;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public ErrorStructure getError() {
        return error;
    }

    public void setError(ErrorStructure error) {
        this.error = error;
    }
}
