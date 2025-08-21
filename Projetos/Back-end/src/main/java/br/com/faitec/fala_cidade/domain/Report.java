package br.com.faitec.fala_cidade.domain;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Report {
    private int id;
    private String description;
    private String rua;
    private String bairro;
    private String urlFoto;
    private String urlVideo;
    private ReportType tipo;


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

    public Report(int id, String description, String rua, String bairro, String urlFoto, String urlVideo, ReportType tipo) {
        this.id = id;
        this.description = description;
        this.rua = rua;
        this.bairro = bairro;
        this.urlFoto = urlFoto;
        this.urlVideo = urlVideo;
        this.tipo = tipo;
    }

    public Report(){}

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

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
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

    public ReportType getTipo() {
        return tipo;
    }

    public void setTipo(ReportType tipo) {
        this.tipo = tipo;
    }
}
