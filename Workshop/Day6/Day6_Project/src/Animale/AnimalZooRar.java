package Animale;

import Animale.Animal;
import Exceptions.AnimalManancaAnimalException;
import Exceptions.AnimalManancaOmException;
import Persoane.AngajatZoo;

/**
 * Created by Andreea.Dima on 7/4/2017.
 */
public class AnimalZooRar extends Animal {
    private String nume;
    private String numeleTariiDeOrigine;

    public AnimalZooRar(){
        System.out.println("Animale.AnimalZooRar nou");
    }

    public AnimalZooRar(String nume){
        this.nume = nume;
        System.out.println("Animale.AnimalZooRar nou numit " + this.nume);
    }

    public AnimalZooRar(String nume,String numeleTara){
        this.nume = nume;
        this.numeleTariiDeOrigine = numeleTara;
        System.out.println("Animale.AnimalZooRar nou numit " + this.nume + " provenit din " + this.numeleTariiDeOrigine );
    }

    @Override
    public void mananca(Object o) {
        if(o instanceof AngajatZoo){
            throw new AnimalManancaOmException("Animale.AnimalZooRar periculos pentru om");
        }
        else if(o instanceof  Animal){
            throw  new AnimalManancaAnimalException("Animale.AnimalZooRar periculos pentru animale");
        }
        else{
            System.out.println("Animale.AnimalZooRar mananca");
        }
    }

    @Override
    public void seJoaca() {
        System.out.println("AnimalulZooRar se joaca");
        super.doarme();
    }

    @Override
    public void faceBaie() {
        System.out.println("AnimalulZooRar face baie");
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNumeleTariiDeOrigine() {
        return numeleTariiDeOrigine;
    }

    public void setNumeleTariiDeOrigine(String numeleTariiDeOrigine) {
        this.numeleTariiDeOrigine = numeleTariiDeOrigine;
    }


}
