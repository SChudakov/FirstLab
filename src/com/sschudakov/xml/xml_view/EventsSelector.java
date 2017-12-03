package com.sschudakov.xml.xml_view;

import com.sschudakov.xml.bin.Event;
import com.sschudakov.xml.bin.Events;
import com.sschudakov.xml.utils.DateParser;

import javax.swing.*;
import javax.xml.datatype.DatatypeConfigurationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 02.12.2017.
 */
public class EventsSelector {
    private javax.swing.JCheckBox facultyCheckBox;
    private javax.swing.JCheckBox subFacultyCheckBox;
    private javax.swing.JCheckBox branchOfStudyCheckBox;
    private javax.swing.JCheckBox dateCheckBox;
    private javax.swing.JCheckBox firstNameCheckBox;
    private javax.swing.JCheckBox middleNameCheckBox;
    private javax.swing.JCheckBox lastNameCheckBox;

    private javax.swing.JComboBox<String> facultyComboBox;
    private javax.swing.JComboBox<String> branchOfStudyComboBox;
    private javax.swing.JComboBox<String> subFacultyComboBox;

    private javax.swing.JTextField fromTextField;
    private javax.swing.JTextField toTextField;

    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JTextField middleNameTextFiled;
    private javax.swing.JTextField lastNameTextField;

    public EventsSelector(JCheckBox facultyCheckBox, JCheckBox subFacultyCheckBox,
                          JCheckBox branchOfStudyCheckBox, JCheckBox dateCheckBox,
                          JCheckBox firstNameCheckBox, JCheckBox middleNameCheckBox,
                          JCheckBox lastNameCheckBox, JComboBox<String> facultyComboBox,
                          JComboBox<String> branchOfStudyComboBox, JComboBox<String> subFacultyComboBox,
                          JTextField fromTextField, JTextField toTextField,
                          JTextField firstNameTextField, JTextField middleNameTextFiled, JTextField lastNameTextField) {
        this.facultyCheckBox = facultyCheckBox;
        this.subFacultyCheckBox = subFacultyCheckBox;
        this.branchOfStudyCheckBox = branchOfStudyCheckBox;
        this.dateCheckBox = dateCheckBox;
        this.firstNameCheckBox = firstNameCheckBox;
        this.middleNameCheckBox = middleNameCheckBox;
        this.lastNameCheckBox = lastNameCheckBox;
        this.facultyComboBox = facultyComboBox;
        this.branchOfStudyComboBox = branchOfStudyComboBox;
        this.subFacultyComboBox = subFacultyComboBox;
        this.fromTextField = fromTextField;
        this.toTextField = toTextField;
        this.firstNameTextField = firstNameTextField;
        this.middleNameTextFiled = middleNameTextFiled;
        this.lastNameTextField = lastNameTextField;
    }

    public List<Event> selectEvents(Events events) throws DatatypeConfigurationException {
        List<Event> result = events.getEvent();

        if (this.facultyCheckBox.isSelected()) {
            result = selectByFaculty(result);
        }
        if (this.subFacultyCheckBox.isSelected()) {
            result = selectBySubFaculty(result);
        }
        if (this.branchOfStudyCheckBox.isSelected()) {
            result = selectByBranchOfStudy(result);
        }
        if (this.dateCheckBox.isSelected()) {
            result = selectByDate(result);
        }
        if (this.firstNameCheckBox.isSelected()) {
            System.out.println("is selected");
            result = selectFirstName(result);
        }
        if (this.middleNameCheckBox.isSelected()) {
            result = selectMiddleName(result);
        }
        if (this.lastNameCheckBox.isSelected()) {
            result = selectLastName(result);
        }
        return result;
    }

    private List<Event> selectByFaculty(List<Event> eventList) {
        String selectedItem = (String) this.facultyComboBox.getSelectedItem();
        if (selectedItem == null || "".equals(selectedItem)) {
            throw new IllegalArgumentException("no one faculty is selected");
        }
        List<Event> result = new ArrayList<>();
        for (Event event : eventList) {
            if (event.getFaculty().equals(selectedItem)) {
                result.add(event);
            }
        }
        return result;
    }

    private List<Event> selectBySubFaculty(List<Event> eventList) {
        String selectedItem = (String) this.subFacultyComboBox.getSelectedItem();
        if (selectedItem == null || "".equals(selectedItem)) {
            throw new IllegalArgumentException("no one sub-faculty is selected");
        }
        List<Event> result = new ArrayList<>();
        for (Event event : eventList) {
            if (event.getSubFaculty().equals(selectedItem)) {
                result.add(event);
            }
        }
        return result;
    }

    private List<Event> selectByBranchOfStudy(List<Event> eventList) {
        String selectedItem = (String) this.branchOfStudyComboBox.getSelectedItem();
        if (selectedItem == null || "".equals(selectedItem)) {
            throw new IllegalArgumentException("no one branch of study is selected");
        }
        List<Event> result = new ArrayList<>();
        for (Event event : eventList) {
            if (event.getBranchOfStudy().equals(selectedItem)) {
                result.add(event);
            }
        }
        return result;
    }

    private List<Event> selectByDate(List<Event> eventList) throws DatatypeConfigurationException {
        List<Event> result = new ArrayList<>();
        LocalDateTime begin = DateParser.parseLocalDateTime(this.fromTextField.getText());
        LocalDateTime end = DateParser.parseLocalDateTime(this.toTextField.getText());
        for (Event event : eventList) {
            if (event.getTime().isBetween(begin, end)) {
                result.add(event);
            }
        }
        return result;
    }

    private List<Event> selectFirstName(List<Event> eventList) {
        String firstName = this.firstNameTextField.getText();
        if (firstName == null || "".equals(firstName)) {
            throw new IllegalArgumentException("no one first name is selected");
        }
        List<Event> result = new ArrayList<>();
        for (Event event : eventList) {
            if (event.getLastFirstMiddleName().getFirstName().equals(firstName)) {
                result.add(event);
            }
        }
        return result;
    }

    private List<Event> selectMiddleName(List<Event> eventList) {
        String firstName = this.middleNameTextFiled.getText();
        if (firstName == null || "".equals(firstName)) {
            throw new IllegalArgumentException("no one middle name is selected");
        }
        List<Event> result = new ArrayList<>();
        for (Event event : eventList) {
            if (event.getLastFirstMiddleName().getMiddleName().equals(firstName)) {
                result.add(event);
            }
        }
        return result;
    }

    private List<Event> selectLastName(List<Event> eventList) {
        String firstName = this.lastNameTextField.getText();
        if (firstName == null || "".equals(firstName)) {
            throw new IllegalArgumentException("no one last name is selected");
        }
        List<Event> result = new ArrayList<>();
        for (Event event : eventList) {
            if (event.getLastFirstMiddleName().getLastName().equals(firstName)) {
                result.add(event);
            }
        }
        return result;
    }

}
