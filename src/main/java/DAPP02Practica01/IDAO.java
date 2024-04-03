package DAPP02Practica01;

import java.util.List;

public interface IDAO<T> {

    public boolean guardar(T p);

    public boolean modificar(T p);

    public List<T> buscarAll();

    public T buscarById(long id);

    public boolean eliminar(T p);

}