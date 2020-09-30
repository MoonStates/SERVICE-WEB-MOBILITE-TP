package metier;
//Cette methode definira la nouvelle question a etre posé. il doit gerer aussi le non repetition de questions
public class AgentScenariste {
    int futurEtat;
    AgentScenariste(){

    }

    public Question nouvelleQuestion(int fE){
        this.futurEtat=fE;

        return new Question("question","reponse",0);
    }
}
