package database;

import domain.Vink;
import java.util.ArrayList;
public interface VinkDAO {

    void addVink(Vink vink);

    void createOrResetTables();

    void createTablesIfNotExist();
    
    boolean deleteVink(Integer databaseID);

    ArrayList<Vink> getAllVinks();

    boolean updateVink(Vink updatedVink);
}
