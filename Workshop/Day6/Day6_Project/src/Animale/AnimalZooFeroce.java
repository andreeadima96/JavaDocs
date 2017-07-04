package Animale;

import Animale.Animal;
import Exceptions.AnimalManancaAnimalException;
import Exceptions.AnimalManancaOmException;
import Persoane.AngajatZoo;

/**
 * Created by Andreea.Dima on 7/4/2017.
 */
public class AnimalZooFeroce extends Animal {

    @Override
    public void doarme() {
        super.doarme();
        System.out.println("Animalul feroce vaneaza");
    }

    @Override
   public void mananca(Object o) {
        if(o instanceof AngajatZoo){
            throw new AnimalManancaOmException("Animale.AnimalZooFeroce periculos pentru oameni");
        }
        else if (o instanceof Animal){
            throw new AnimalManancaAnimalException("Animale.AnimalZooFeroce periculos pentru animale");
        }
        else{
            System.out.println("Animale.AnimalZooFeroce mananca");
        }
    }

    @Override
    public void seJoaca() {
        System.out.println("Animale.AnimalZooFeroce se joaca");
    }

    @Override
   public void faceBaie() {
        System.out.println("Animale.AnimalZooFeroce face baie");
    }
}
