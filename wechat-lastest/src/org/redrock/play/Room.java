package org.redrock.play;

public class Room {
    private Integer id;
    private String openner;
    private Integer room_num;
    private Integer total_num;
    private Integer wodi_num;
    private Integer pingmin_num;
    private String wodici;
    private String pingminci;
    private Long expiretime;

    public Long getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(Long expiretime) {
        this.expiretime = expiretime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenner() {
        return openner;
    }

    public void setOpenner(String openner) {
        this.openner = openner;
    }

    public Integer getRoom_num() {
        return room_num;
    }

    public void setRoom_num(Integer room_num) {
        this.room_num = room_num;
    }

    public Integer getTotal_num() {
        return total_num;
    }

    public void setTotal_num(Integer total_num) {
        this.total_num = total_num;
    }

    public Integer getWodi_num() {
        return wodi_num;
    }

    public void setWodi_num(Integer wodi_num) {
        this.wodi_num = wodi_num;
    }

    public Integer getPingmin_num() {
        return pingmin_num;
    }

    public void setPingmin_num(Integer pingmin_num) {
        this.pingmin_num = pingmin_num;
    }

    public String getWodici() {
        return wodici;
    }

    public void setWodici(String wodici) {
        this.wodici = wodici;
    }

    public String getPingminci() {
        return pingminci;
    }

    public void setPingminci(String pingminci) {
        this.pingminci = pingminci;
    }


}
