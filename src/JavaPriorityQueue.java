// Problem from https://www.hackerrank.com/challenges/java-priority-queue/problem?isFullScreen=true
// Input example:
/*
12
ENTER John 3.75 50
ENTER Mark 3.8 24
ENTER Shafaet 3.7 35
SERVED
SERVED
ENTER Samiha 3.85 36
SERVED
ENTER Ashley 3.9 42
ENTER Maria 3.6 46
ENTER Anik 3.95 49
ENTER Dan 3.95 50
SERVED

// Output example
Dan
Ashley
Shafaet
Maria
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
 * Create the Student and Priorities classes here.
 */
class Student {
    private final String name;
    private final double cgpa;
    private final int id;
    private boolean served;
    
    public Student(int id, String name, double cgpa) {
        this.name = name;
        this.cgpa = cgpa;
        this.id = id;
        this.served = false;
    }
    public void setServed(boolean served) {
        this.served = served;
    }
    public String getName() {
        return this.name;
    }
    public double getCGPA() {
        return cgpa;
    }
    public int getID() {
        return id;
    }
    public boolean isServed() {
        return served;
    }
}

class Priorities {
    
    /* 
     * The waiting list stores the students to be
     * served in order. In a stack format, or LIFO
     * meaning that the first element (index 0) is
     * the first one to be served, and last element
     * is the last one to be served
     */
    List<Student> waitingList = new ArrayList<>();
    
    /*
     * The served list stores the served students
     * in the order they're served.
     */
    List<Student> servedList = new ArrayList<>();
    
    /**
     * Gets the list of served studens ordered in the order
     * that they were served.
     *
     * @param events List of messages containing the event,
     *        student name, id and cgpa score
     * @returns List of Students ordered in the order that
     *          they were served.
     */
    public List<Student> getStudents(List<String> events) {
        // Processes the list of events
        // Event sample: ENTER John 3.75 50
        for (String event : events) {
            // Get the data from the event message
            String[] parts = event.split(" ");
            String eventName = parts[0];
                
            // ordering criteria
            // 1. student with highest cgpa is served first
            // 2. students with same cgpa are served ordered by name
            // 3. students with same cgpa and name are served ordered by id
            if (eventName.equals("ENTER")) {
                String name = parts[1];
                double cgpa = Double.parseDouble(parts[2]);
                int id = Integer.parseInt(parts[3]);
                // Create the Student
                Student current = new Student(id, parts[1], cgpa);

                waitingList.add(current);
                reorderWaitList();
            }
            else {
                if (!waitingList.isEmpty()) {
                    Student next = waitingList.remove(0);
                    servedList.add(next);    
                }
            }
        }
        return waitingList;
    }
    
    private void reorderWaitList() {
        waitingList.sort((Student s1, Student s2) -> {
            if (s1.getCGPA() > s2.getCGPA()) {
                return -1;
            }
            else if (Double.compare(s1.getCGPA(), s2.getCGPA()) == 0) {
                if (s1.getName().equals(s2.getName())) {
                    return s1.getID() - s2.getID();
                }
                else {
                    return s1.getName().compareTo(s2.getName());
                }
            }
            else {
                return 1;
            }
        });
    }
}

public class Solution {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();
    
    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());    
        List<String> events = new ArrayList<>();
        
        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }
        
        List<Student> students = priorities.getStudents(events);
        
        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }
    }
}
