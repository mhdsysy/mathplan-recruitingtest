//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2020.04.22 um 11:12:40 AM CEST 
//


package de.mathplan.recruitingtest.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für university complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="university"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="lectures" type="{}lectures"/&gt;
 *         &lt;element name="curricula" type="{}curricula"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "university", propOrder = {
    "lectures",
    "curricula"
})
@XmlRootElement(name = "university")
public class University
    extends Base
{

    @XmlElementWrapper(required = true)
    @XmlElement(name = "lecture")
    protected List<Lecture> lectures = new ArrayList<Lecture>();
    @XmlElementWrapper(required = true)
    @XmlElement(name = "curriculum")
    protected List<Curriculum> curricula = new ArrayList<Curriculum>();

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public List<Curriculum> getCurricula() {
        return curricula;
    }

    public void setCurricula(List<Curriculum> curricula) {
        this.curricula = curricula;
    }

}
