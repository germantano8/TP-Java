package main.java.Logic;
import java.util.List;
import main.java.Data.*;
import main.java.Entities.*;
import java.sql.SQLException;


public class LogicService {
    private DataService dataService;

    public LogicService(){
        dataService = new DataService();
    }

    public List<Service> getAll() throws SQLException {
        return dataService.getAll();
    }

    public Service getOne(int id) throws SQLException{
        return dataService.getOne(id);
    }

    public void addService(Service service) throws SQLException{
       dataService.addService(service); //realmente es necesario ponerlo en la capa de logica? podríamos hacer directamente
                                        //un dataService.addService(service) cuando se requiera llamarlo, en vez de
                                        //logicService.addService(service)
    }

    public void updateService(Service service) throws SQLException{
        dataService.updateService(service);
    }

    public void deleteService(Service service) throws SQLException{
        dataService.deleteService(service);
    }
}
