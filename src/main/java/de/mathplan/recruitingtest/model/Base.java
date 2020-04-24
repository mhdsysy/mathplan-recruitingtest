package de.mathplan.recruitingtest.model;

import javax.xml.bind.Unmarshaller;

/**
 *
 * @author hoener
 * 
 * The base class all JAXB classes inherit from. It basically offers
 * functionality to get the parent of an JAXB object
 * 
 */
public abstract class Base extends Unmarshaller.Listener {

    protected Object parent;

    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
        this.parent = parent;
    }

    public Object getParent() {
        return parent;
    }
}
