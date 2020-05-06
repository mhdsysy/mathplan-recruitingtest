package de.mathplan.recruitingtest;

import de.mathplan.recruitingtest.model.Booking;
import de.mathplan.recruitingtest.model.Conflict;
import de.mathplan.recruitingtest.model.SelfBalancingBinarySearchTree;
import de.mathplan.recruitingtest.model.University;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author hoener
 */
public class ConflictFinder {

    private static final String UNIVERSITY_XML = "src/main/resources/timetable.xml";
    private static final String SCHEMA = "src/main/resources/timetable.xsd";

    public static void main(String[] args) throws JAXBException {

        // to read the xml we are using the JAXB framework
        // the following method reads the xml and stores it as a java object
        University uni = unmarshall(UNIVERSITY_XML, SCHEMA);
        System.out.println("read " + UNIVERSITY_XML + " successfully");


//      Hashmap to store (booking, curriculum) as (key, value) where curriculum.
//      Is the curriculum to which the booking belongs.
        HashMap<Booking, String> bookingCurriculum = new HashMap<Booking, String>();

//      Hashmap to store (weekday, tree) as (key, value) where tree is
//      the tree that stors all the booking on that particular day.
        HashMap<String, SelfBalancingBinarySearchTree> weekdayTree = new HashMap<String, SelfBalancingBinarySearchTree>();


        uni.getCurricula().forEach(curriculum -> curriculum.getLecture()
                .forEach(lectureJAXBElement -> lectureJAXBElement.getValue().getRoombookings()
                        .forEach(booking ->
                            bookingCurriculum.put(booking, curriculum.getName()))));


        uni.getLectures()
                .forEach(lecture -> lecture.getRoombookings()
                        .forEach(booking -> {
                            if (weekdayTree.get(booking.getWeekday()) == null) {
                                weekdayTree.put(booking.getWeekday(), new SelfBalancingBinarySearchTree());
                            }
                            weekdayTree.get(booking.getWeekday()).insertRec(booking, bookingCurriculum.get(booking));
                                }
                        ));


        List<Conflict> conflicts = new ArrayList<>();
        uni.getLectures()
                .forEach(lecture -> lecture.getRoombookings()
                        .forEach(booking -> {
                            SelfBalancingBinarySearchTree tree =  weekdayTree.get(booking.getWeekday());
                                    conflicts.addAll(tree.findOverlappingIntervals(tree.getRoot(), booking,
                                            bookingCurriculum.get(booking)));
                                    tree.delete(tree.getRoot(), booking);
                                    weekdayTree.put(booking.getWeekday(), tree);
                                }
                        ));
        conflicts.forEach(
                conflict ->
                        System.out.println(conflict.getB1().getStartTime() + " " + conflict.getB1().getEndTime()  + " " +
                                bookingCurriculum.get(conflict.getB1())+ " " + conflict.getB1().getRoom()+ " -- " +
                                conflict.getB2().getStartTime() + " " + conflict.getB2().getEndTime()+ " " +
                                bookingCurriculum.get(conflict.getB2())+ " " + conflict.getB2().getRoom() + " :  " + conflict.getName()
                        + "  On " + conflict.getB1().getWeekday())
        );

//        selfBalancingBinarySearchTree.inorder(selfBalancingBinarySearchTree.getRoot());
//        System.out.println();

    }

    private static University unmarshall(String xmlPath, String schemaPath) {
        try {
            File xmlFile = new File(xmlPath);
            File schemaFile = new File(schemaPath);

            // creating the JAXB unmarshaller
            JAXBContext jc = JAXBContext.newInstance(University.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            // creating the schema factory and setting it for xsd validation
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(schemaFile);
            unmarshaller.setSchema(schema);

            // unmarshalling the xml
            JAXBElement<University> element = (JAXBElement<University>) unmarshaller.unmarshal(xmlFile);
            University university = element.getValue();
            return university;
        } catch (JAXBException | SAXException ex) {
            Logger.getLogger(ConflictFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
