/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.util.List;

/**
 *
 * @author wulberri
 * @param <T>
 */
public interface IDAO<T> {
    public boolean crear(T nuevo);
    public List<T> consultar();
    public boolean actualizar(T nuevosDatos);
    public boolean eliminar(String id);
    public T buscarPorId(String id);
}
