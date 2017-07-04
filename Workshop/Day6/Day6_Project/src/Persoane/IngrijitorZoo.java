package Persoane;

import Animale.Animal;
import Animale.AnimalZooRar;
import Exceptions.AnimalPeCaleDeDisparitieException;
import Persoane.AngajatZoo;

/**
 * Created by Andreea.Dima on 7/4/2017.
 */
public class IngrijitorZoo implements AngajatZoo {
    int bonusIngrijitor = 0;
    String name;

    public IngrijitorZoo(){
        this.name = "undefined";
    }

    public IngrijitorZoo (String name){
        this.name = name;
    }

    @Override
    public void lucreaza(Animal animal) {
        System.out.println("Ingrijitorul intra in cusca animalului");
        bonusIngrijitor++;
    }

    @Override
    public void calculeazaBonusSalarial() {
        System.out.println("Bonusul salarial pentru Ingrijitor este: " + 2*bonusIngrijitor);
    }

    public void lucreaza(Animal animal, Object mancare) throws AnimalPeCaleDeDisparitieException {
        this.lucreaza(animal);
        animal.faceBaie();
        animal.doarme();
        animal.seJoaca();
        animal.mananca(mancare);

        if(animal instanceof AnimalZooRar && mancare == null){
            throw new AnimalPeCaleDeDisparitieException("Animale.AnimalZooRar");
        }
    }
}
