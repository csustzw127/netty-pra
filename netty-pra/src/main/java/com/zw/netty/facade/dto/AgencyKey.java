package com.zw.netty.facade.dto;
import java.util.Date;

public class AgencyKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cb_ae_agency.acctnbr
     *
     * @mbg.generated Tue Jan 29 22:12:11 CST 2019
     */
    private String acctnbr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cb_ae_agency.postdate
     *
     * @mbg.generated Tue Jan 29 22:12:11 CST 2019
     */
    private Date postdate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cb_ae_agency.acctnbr
     *
     * @return the value of cb_ae_agency.acctnbr
     *
     * @mbg.generated Tue Jan 29 22:12:11 CST 2019
     */
    public String getAcctnbr() {
        return acctnbr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cb_ae_agency.acctnbr
     *
     * @param acctnbr the value for cb_ae_agency.acctnbr
     *
     * @mbg.generated Tue Jan 29 22:12:11 CST 2019
     */
    public void setAcctnbr(String acctnbr) {
        this.acctnbr = acctnbr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cb_ae_agency.postdate
     *
     * @return the value of cb_ae_agency.postdate
     *
     * @mbg.generated Tue Jan 29 22:12:11 CST 2019
     */
    public Date getPostdate() {
        return postdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cb_ae_agency.postdate
     *
     * @param postdate the value for cb_ae_agency.postdate
     *
     * @mbg.generated Tue Jan 29 22:12:11 CST 2019
     */
    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }
}