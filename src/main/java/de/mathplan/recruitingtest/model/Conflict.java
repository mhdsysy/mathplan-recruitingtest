package de.mathplan.recruitingtest.model;

import java.util.Objects;

/**
 *
 * @author hoener
 */
public class Conflict {

    private final String name;
    private final Booking b1;
    private final Booking b2;

    public Conflict(String room, Booking b1, Booking b2) {
        this.name = room;
        this.b1 = b1;
        this.b2 = b2;
    }

    @Override
    public String toString() {
        return String.format("%s: %s (%s %s - %s), %s (%s %s - %s)",
                name,
                ((Lecture) b1.getParent()).getName(),
                b1.getWeekday(),
                b1.getStartTime(),
                b1.getEndTime(),
                ((Lecture) b2.getParent()).getName(),
                b2.getWeekday(),
                b2.getStartTime(),
                b2.getEndTime()
        );
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = hash + Objects.hashCode(this.b1);
        hash = hash + Objects.hashCode(this.b2);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Conflict other = (Conflict) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!(this.b1.equals(other.b1) && this.b2.equals(other.b2))
                && !(this.b1.equals(other.b2) && this.b2.equals(other.b1))) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public Booking getB1() {
        return b1;
    }

    public Booking getB2() {
        return b2;
    }
}
