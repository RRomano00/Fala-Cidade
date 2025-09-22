package br.com.faitec.fala_cidade.domain;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Report {
    private int id;
    private String description;
    private String neighborhood;
    private String number;
    private String street;
    private String city;
    private String urlFotoVideo;
    private ReportStatus status;
    private ReportType type;
    private String email;
    private int users_id;


    public enum ReportType {
        BURACO_NA_RUA_OU_CALCADA,
        POSTE_COM_LUZ_QUEIMADA,
        LIXO_ACUMULADO_OU_TERRENO_SUJO,
        SINALIZACAO_OU_SEMAFORO_COM_DEFEITO,
        PROBLEMAS_EM_PRACAS_E_PARQUES,
        FALHAS_NO_TRANSPORTE_PUBLICO,
        PROBLEMAS_EM_POSTO_DE_SAUDE_OU_ESCOLA,
        SOM_ALTO_OU_PERTURBACAO_DO_SOSSEGO,
        OBRA_IRREGULAR_OU_IMOVEL_ABANDONADO,
        MAUS_TRATOS_AOS_ANIMAIS,
        PESSOA_PRECISANDO_DE_AJUDA,
        OUTROS_PROBLEMAS
    }

    public enum ReportStatus {
        PENDENTE,
        EM_ANDAMENTO,
        ATENDIDA
    }

    public Report(int id, String description, String neighborhood, String number, String street, String city, String urlFotoVideo, ReportStatus status, ReportType type) {
        this.id = id;
        this.description = description;
        this.neighborhood = neighborhood;
        this.number = number;
        this.street = street;
        this.city = city;
        this.urlFotoVideo = urlFotoVideo;
        this.status = status;
        this.type = type;
    }

    public Report() {
    }

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
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

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }
}