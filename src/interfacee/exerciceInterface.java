/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacee;

import entity.exercice;
import entity.typeExercice;
import java.util.List;

/**
 *
 * @author louay
 */
public interface exerciceInterface {

    public void addExercice(typeExercice type, exercice exc);

    public void editExercice(exercice exc);

    public void deleteExercice(int id);

    public List<exercice> readExercice();

}
