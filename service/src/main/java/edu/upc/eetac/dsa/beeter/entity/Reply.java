package edu.upc.eetac.dsa.beeter.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reply
{
    private String id;
    private String stingid;
    private String userid;
    private String text;
    private long created_at;

    public Reply(String id, String stingid, String userid, String text)
    {
        this.id = id;
        this.stingid = stingid;
        this.userid = userid;
        this.text = text;
    }

}
