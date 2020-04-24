//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2020.04.22 um 11:12:40 AM CEST 
//


package de.mathplan.recruitingtest.model;

import java.time.LocalTime;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, LocalTime>
{


    public LocalTime unmarshal(String value) {
        return (de.mathplan.recruitingtest.converter.TimeConverter.parseDate(value));
    }

    public String marshal(LocalTime value) {
        return (de.mathplan.recruitingtest.converter.TimeConverter.printDate(value));
    }

}
