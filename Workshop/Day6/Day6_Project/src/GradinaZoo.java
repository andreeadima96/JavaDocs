import Animale.AnimalZooRar;
import Persoane.IngrijitorZoo;

import java.util.Date;

/**
 * Created by Andreea.Dima on 7/4/2017.
 */
public final class GradinaZoo {
    private final String denumireGradinaZoo;
    private final Date dataDeschideriiGradinii;
    private final AnimalZooRar animalRar ;
    private final IngrijitorZoo angajatulLunii;

    public GradinaZoo(String denumire, Date data, AnimalZooRar animalRar, IngrijitorZoo angajatulLunii){
        this.denumireGradinaZoo = denumire;
        this.dataDeschideriiGradinii = data;
        this.animalRar = animalRar;
        this.angajatulLunii = angajatulLunii;
    }

    public String getDenumireGradinaZoo() {
        return this.denumireGradinaZoo;
    }

    public Date getDataDeschideriiGradinii() {
        return this.dataDeschideriiGradinii;
    }

    public AnimalZooRar getAnimalRar() {
        return this.animalRar;
    }
    public IngrijitorZoo getAngajatulLunii() {
        return this.angajatulLunii;
    }
}
