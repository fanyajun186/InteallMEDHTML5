
package com.inteall.image.org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SelSumResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "selSumResult"
})
@XmlRootElement(name = "SelSumResponse")
public class SelSumResponse {

    @XmlElement(name = "SelSumResult")
    protected String selSumResult;

    /**
     * Gets the value of the selSumResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelSumResult() {
        return selSumResult;
    }

    /**
     * Sets the value of the selSumResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelSumResult(String value) {
        this.selSumResult = value;
    }

}
