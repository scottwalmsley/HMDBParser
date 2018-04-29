package com.metabosoft.cm.hmdbparser;

import java.io.Serializable;

public class HmdbPathway implements Serializable {
    private static final long serialVersionUID = 1L;
    private String pathway;

    private String smpd_id;
    private String kegg_mapp_id;

    public String getPathway() {
        return pathway;
    }

    public void setPathway(String pathway) {
        this.pathway = pathway;


    }

    public String getSmpd_id() {
        return smpd_id;
    }

    public void setSmpd_id(String smpd_id) {
        if(smpd_id.length()>0) {
            this.smpd_id = smpd_id;

        }else {
            this.smpd_id = null;
        }
    }

    public String getKegg_mapp_id() {
        return kegg_mapp_id;
    }

    public void setKegg_mapp_id(String kegg_mapp_id) {
        if(kegg_mapp_id.length()> 0 ){
            this.kegg_mapp_id = kegg_mapp_id;

        }else{
            this.kegg_mapp_id = null;
        }


    }
}
