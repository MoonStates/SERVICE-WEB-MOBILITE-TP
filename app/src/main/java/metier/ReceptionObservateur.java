package metier;

public class ReceptionObservateur {
    AgentAnalyste agentAnalyste = new AgentAnalyste();
    AgentScenariste agentScenariste=new AgentScenariste();

    //A mon avis cette premiere version du coté metier devrais juste creer un log qui stockerai s'il y a
    // bien ou mal repondu la question et quelle etait le niveua de cette question

    //Question:         question passé en parametre est celle qui a ete possé a l'utilisateur
    //bonneQuestion:    ce boolean dit si l'utilisateur a repondu correctement a la question
    ReceptionObservateur(Question question, boolean bonneQuestion){
        //Le but de ce constructeur est deffectuer l'insertion des informations passés en parametre dans la table log de la BDD

        //Une fois l'insertion finie, le constructeur doit faure appelle a l'agent analyste pour
        // qu'il puisse evoluer ou pas l'etat
        int furturEtat=agentAnalyste.analyserlog();
        agentScenariste.nouvelleQuestion(furturEtat);

    }
}
