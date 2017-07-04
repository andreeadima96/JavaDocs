package Persoane;

import Animale.Animal;

/**
 * Created by Andreea.Dima on 7/4/2017.
 */
public interface AngajatZoo {
    void lucreaza(Animal animal);

    Integer valoareBonusPerAnimal = 50;

    void calculeazaBonusSalarial();
}
