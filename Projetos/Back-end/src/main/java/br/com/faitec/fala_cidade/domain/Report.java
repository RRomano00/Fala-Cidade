package br.com.faitec.fala_cidade.domain;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Report {
    private int id;
    private String description;
    private String neighborhood;
    private int number;
    private String street;
    private String city;
    private String cep;
    private String urlFoto;
    private String urlVideo;
    private ReportSituation situation;
    private ReportType type;


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
        PESSOA_PRECISANDO_DE_AJUDA_SOCIAL,
        OUTROS_PROBLEMAS
    }

    public enum ReportSituation {
        PENDENTE,
        EM_ANDAMENTO,
        CONCLUIDO
    }

    public Report(int id, String description, String neighborhood, int number, String street, String city, String cep, String urlFoto, String urlVideo, ReportSituation situation, ReportType type) {
        this.id = id;
        this.description = description;
        this.neighborhood = neighborhood;
        this.number = number;
        this.street = street;
        this.city = city;
        this.cep = cep;
        this.urlFoto = urlFoto;
        this.urlVideo = urlVideo;
        this.situation = situation;
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public ReportSituation getSituation() {
        return situation;
    }

    public void setSituation(ReportSituation situation) {
        this.situation = situation;
    }

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }
}