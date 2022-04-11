/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacee;

import entity.typeExercice;
import java.util.List;

/**
 *
 * @author louay
 */
public interface typeExerciceInterface {

    public void addType(typeExercice type);

    public void editType(typeExercice type);

    public void deleteType(int id);

    public List<typeExercice> readType();

}
