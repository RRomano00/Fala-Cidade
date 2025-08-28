package br.com.faitec.fala_cidade.domain.dto;

import br.com.faitec.fala_cidade.domain.Report;

public class GetReport {
    private int id;
    private String description;
    private String neighborhood;
    private int number;
    private String street;
    private String city;
    private String urlFotoVideo;
    private Report.ReportStatus status;
    private Report.ReportType type;
    private String email;
    private String fullname;

    public GetReport(int id, String description, String neighborhood, int number, String street, String city, String urlFotoVideo, Report.ReportStatus status, Report.ReportType type, String email, String fullname) {
        this.id = id;
        this.description = description;
        this.neighborhood = neighborhood;
        this.number = number;
        this.street = street;
        this.city = city;
        this.urlFotoVideo = urlFotoVideo;
        this.status = status;
        this.type = type;
        this.email = email;
        this.fullname = fullname;
    }
    public GetReport(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUrlFotoVideo() {
        return urlFotoVideo;
    }

    public void setUrlFotoVideo(String urlFotoVideo) {
        this.urlFotoVideo = urlFotoVideo;
    }

    public Report.ReportStatus getStatus() {
        return status;
    }

    public void setStatus(Report.ReportStatus status) {
        this.status = status;
    }

    public Report.ReportType getType() {
        return type;
    }

    public void setType(Report.ReportType type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
