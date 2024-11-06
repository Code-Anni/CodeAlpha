import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class StudentGradeTracker {
    private final ArrayList<Double> grades;

    public StudentGradeTracker() {
        grades = new ArrayList<>();
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public double computeAverage() {
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return grades.isEmpty() ? 0 : sum / grades.size();
    }

    public double getHighestGrade() {
        return grades.isEmpty() ? 0 : Collections.max(grades);
    }

    public double getLowestGrade() {
        return grades.isEmpty() ? 0 : Collections.min(grades);
    }

    public void displayGrades() {
        System.out.println("Grades: " + grades);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            StudentGradeTracker tracker = new StudentGradeTracker();
            boolean continueInput = true;
            
            while (continueInput) {
                System.out.print("Enter student grade (or type 'done' to finish): ");
                String input = scanner.next();
                
                if (input.equalsIgnoreCase("done")) {
                    continueInput = false;
                } else {
                    try {
                        double grade = Double.parseDouble(input);
                        tracker.addGrade(grade);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid grade or 'done' to finish.");
                    }
                }
            }
            
            tracker.displayGrades();
            System.out.println("Average Grade: " + tracker.computeAverage());
            System.out.println("Highest Grade: " + tracker.getHighestGrade());
            System.out.println("Lowest Grade: " + tracker.getLowestGrade());
        }
    }
}
