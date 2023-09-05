package WorkingWithAbstractionLab.T03StudentSystem;

import java.util.HashMap;
import java.util.Map;

public class StudentSystem {
    private final Map<String, Student> repo;

    public StudentSystem() {
        this.repo = new HashMap<>();
    }

    public void ParseCommand(String[] input) {

        String command = input[0];

        if ("Create".equals(command)) {

            create(input);

        } else if ("Show".equals(command)) {

            show(input);

        }

    }

    private void create(String[] input) {

        String name = input[1];
        int age = Integer.parseInt(input[2]);
        double grade = Double.parseDouble(input[3]);

        repo.putIfAbsent(name, new Student(name, age, grade));

    }

    private void show(String[] input) {

        String name = input[1];

        if (repo.containsKey(name)) {

            Student student = repo.get(name);
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%s is %s years old.", student.getName(), student.getAge()));

            if (student.getGrade() >= 5.00) {

                sb.append(" Excellent student.");

            } else if (student.getGrade() < 5.00 && student.getGrade() >= 3.50) {

                sb.append(" Average student.");

            } else {

                sb.append(" Very nice person.");

            }

            System.out.println(sb);

        }

    }
}
