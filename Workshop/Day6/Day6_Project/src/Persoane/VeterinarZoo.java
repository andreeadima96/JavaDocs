package Persoane;

import Animale.Animal;
import Animale.AnimalZooFeroce;
import Persoane.AngajatZoo;

/**
 * Created by Andreea.Dima on 7/4/2017.
 */
public class VeterinarZoo implements AngajatZoo {
    int bonusVeterinar = 0;
    @Override
    public void lucreaza(Animal animal) {
        System.out.println("Veterinarul are grija de animal");

        if(animal instanceof AnimalZooFeroce) {
            animal.faceBaie();
        }

        bonusVeterinar++;
    }

    @Override
    public void calculeazaBonusSalarial() {
        System.out.println("Bonus salarial pentru veterinar: " + 3*bonusVeterinar);
    }
}
