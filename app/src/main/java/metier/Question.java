package metier;

public class Question {
    String texteDeLaQuestion;
    String bonneReponse;
    int difficulteQuestion;
    Question(String q, String b, int d){
        this.texteDeLaQuestion=q;
        this.bonneReponse=b;
        this.difficulteQuestion=d;
    }
}
