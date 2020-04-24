//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2020.04.22 um 11:12:40 AM CEST 
//


package de.mathplan.recruitingtest.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.mathplan.recruitingtest.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _University_QNAME = new QName("", "university");
    private final static QName _CurriculumLecture_QNAME = new QName("", "lecture");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.mathplan.recruitingtest.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link University }
     * 
     */
    public University createUniversity() {
        return new University();
    }

    /**
     * Create an instance of {@link Lecture }
     * 
     */
    public Lecture createLecture() {
        return new Lecture();
    }

    /**
     * Create an instance of {@link Booking }
     * 
     */
    public Booking createBooking() {
        return new Booking();
    }

    /**
     * Create an instance of {@link Curriculum }
     * 
     */
    public Curriculum createCurriculum() {
        return new Curriculum();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link University }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "university")
    public JAXBElement<University> createUniversity(University value) {
        return new JAXBElement<University>(_University_QNAME, University.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "lecture", scope = Curriculum.class)
    @XmlIDREF
    public JAXBElement<Object> createCurriculumLecture(Object value) {
        return new JAXBElement<Object>(_CurriculumLecture_QNAME, Object.class, Curriculum.class, value);
    }

}
