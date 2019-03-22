package com.ws.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class EsModel{
    private String id;
    private  String kname;
    private  Integer kss;
    private  Integer lls;
    private  String lteacher;
    private  String oktime;
    private  String ktype;
    private  String kdesc;
    private  String kurl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKname() {
        return kname;
    }

    public void setKname(String kname) {
        this.kname = kname;
    }

    public Integer getKss() {
        return kss;
    }

    public void setKss(Integer kss) {
        this.kss = kss;
    }

    public Integer getLls() {
        return lls;
    }

    public void setLls(Integer lls) {
        this.lls = lls;
    }

    public String getLteacher() {
        return lteacher;
    }

    public void setLteacher(String lteacher) {
        this.lteacher = lteacher;
    }

    public String getOktime() {
        return oktime;
    }

    public void setOktime(String oktime) {
        this.oktime = oktime;
    }

    public String getKtype() {
        return ktype;
    }

    public void setKtype(String ktype) {
        this.ktype = ktype;
    }

    public String getKdesc() {
        return kdesc;
    }

    public void setKdesc(String kdesc) {
        this.kdesc = kdesc;
    }

    public String getKurl() {
        return kurl;
    }

    public void setKurl(String kurl) {
        this.kurl = kurl;
    }
}
