package week_8.class_problems;

import java.util.*;

public class Q3_OnlineExam {

    static class Student {

        String name;

        public Student(String name) {
            this.name = name;
        }
    }

    static abstract class Question {

        String questionId;
        int points;

        public Question(String questionId, int points) {
            this.questionId = questionId;
            this.points = points;
        }

        public abstract boolean evaluate(String answer);
    }

    static class MultipleChoiceQuestion extends Question {

        String correctAnswer;

        public MultipleChoiceQuestion(
                String questionId,
                int points,
                String correctAnswer) {

            super(questionId, points);
            this.correctAnswer = correctAnswer;
        }

        @Override
        public boolean evaluate(String answer) {
            return correctAnswer.equals(answer);
        }
    }

    static class TrueFalseQuestion extends Question {

        boolean correctAnswer;

        public TrueFalseQuestion(
                String questionId,
                int points,
                boolean correctAnswer) {

            super(questionId, points);
            this.correctAnswer = correctAnswer;
        }

        @Override
        public boolean evaluate(String answer) {
            return correctAnswer ==
                    Boolean.parseBoolean(answer);
        }
    }

    static class ShortAnswerQuestion extends Question {

        String correctAnswer;

        public ShortAnswerQuestion(
                String questionId,
                int points,
                String correctAnswer) {

            super(questionId, points);
            this.correctAnswer = correctAnswer;
        }

        @Override
        public boolean evaluate(String answer) {
            return correctAnswer.equalsIgnoreCase(answer);
        }
    }

    static class Examination {

        String name;
        ArrayList<Question> questions =
            new ArrayList<>();

        public Examination(String name) {
            this.name = name;
        }

        public void addQuestion(Question question) {
            questions.add(question);
        }
    }

    static class Attempt {

        Student student;
        Examination exam;

        HashMap<Question, String> answers =
            new HashMap<>();

        boolean submitted = false;

        public Attempt(
                Student student,
                Examination exam) {

            this.student = student;
            this.exam = exam;
        }

        public void answer(
                Question question,
                String answer) {

            if (submitted) {
                System.out.println(
                    "Cannot change answers for a submitted examination."
                );
                return;
            }

            answers.put(question, answer);

            System.out.println(
                "Answer recorded for "
                + question.questionId + "."
            );
        }

        public void submit() {

            submitted = true;

            System.out.println(
                exam.name + " submitted by "
                + student.name + "."
            );

            int total = 0;
            int score = 0;

            for (Question question : exam.questions) {

                total += question.points;

                String answer = answers.get(question);

                if (answer != null &&
                        question.evaluate(answer)) {

                    score += question.points;

                    System.out.println(
                        "Question "
                        + question.questionId
                        + ": Correct ("
                        + question.points
                        + " points)"
                    );

                } else {

                    System.out.println(
                        "Question "
                        + question.questionId
                        + ": Incorrect (0 points)"
                    );
                }
            }

            System.out.println(
                "Total score: "
                + score + "/" + total
            );
        }
    }

    public static void main(String[] args) {

        Student student =
            new Student("Student 1");

        Examination exam =
            new Examination("Exam A");

        Question q1 =
            new MultipleChoiceQuestion(
                "1", 5, "C"
            );

        Question q2 =
            new TrueFalseQuestion(
                "2", 5, false
            );

        exam.addQuestion(q1);
        exam.addQuestion(q2);

        Attempt attempt =
            new Attempt(student, exam);

        System.out.println(
            "Exam A started by Student 1."
        );

        attempt.answer(q1, "C");
        attempt.answer(q2, "True");

        attempt.submit();

        attempt.answer(q1, "B");
    }
}