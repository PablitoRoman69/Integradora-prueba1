/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataBase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import dataBase.conexion;
import java.sql.Connection;
import java.sql.ResultSet;

public class metodosUsuario {
    
    private Connection con;
    int id=0;
    String nombre, correo,usuario, password;
    
    conexion conex=new conexion();
    PreparedStatement stmt;
    ResultSet rs;

    public void insertar(String nombre, String correo, String usuario, String password){

        this.nombre=nombre;
        this.correo=correo;
        this.usuario=usuario;
        this.password=password;

        try{
            con=conex.getconexion();
            stmt= con.prepareStatement("insert into usuarios (nombre, correo, usuario, contrasenia) values (?,?,?,?)");
            stmt.setString(1, nombre);
            stmt.setString(2, correo);
            stmt.setString(3,usuario);
            stmt.setString(4, password);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog( null, "Se han insertado los datos");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error de conexión:" + e.getMessage());
        }
    }
    
    public int Consultar(String correo){
    
        this.correo=correo;
        String clave = "";

        try{
            con=conex.getconexion();
            stmt= con.prepareStatement("select id from usuarios where correo=?");
            stmt.setString(1, correo);
            rs=stmt.executeQuery();

            if (rs.next()) {
                clave=rs.getString("id");
                JOptionPane.showMessageDialog( null, "El id es :" + clave);
                id=Integer.parseInt(clave);
            } else {
                JOptionPane.showMessageDialog( null, "No existe una cuenta afiliada a el correo electronico");
            }
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null,"Error de conexión:" + e.getMessage());
        }
        return id;
    }
    
    public void eliminar(int id){
    
        this.id=id;

        try{
            con=conex.getconexion();
            stmt = con.prepareStatement("Delete from usuarios where id=?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog( null, "Usuario eliminado");
            
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null,"Error de conexión:" + e.getMessage());
        }
    }
    
    public void modificar(int id, String password){
    
        this.id=id;

        try{
            con=conex.getconexion();
            stmt = con.prepareStatement("Update usuarios set contrasenia=? where id=?");
            stmt.setString(1, password);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog( null, "Contraseña Cambiada");
            
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null,"Error de conexión:" + e.getMessage());
        }
    }
   
    public void ConsultarUsuario(String usuario, String contrasenia){

        try{
            con=conex.getconexion();
            stmt= con.prepareStatement("select usuario, contrasenia from usuarios where usuario=? and contrasenia=?");
            stmt.setString(1, usuario);
            stmt.setString(2, contrasenia);
            rs=stmt.executeQuery();

            if (rs.next()) {
                this.usuario=rs.getString("usuario");
                this.password=rs.getString("contrasenia");
                JOptionPane.showMessageDialog( null, "BIENVENIDO AL SISTEMA");
            
            } else {
                JOptionPane.showMessageDialog( null, "No existe una cuenta afiliada a el correo electronico");
            }
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null,"Error de conexión:" + e.getMessage());
        }
    }
    
    public  String[] ConsultaGeneral() {
        
        String[] datos=new String[5];
        try{
            con=conex.getconexion();
            stmt= con.prepareStatement("select * from usuarios");
            rs=stmt.executeQuery();
            
            while(rs.next()) {
               datos[0]=rs.getString(1);
               datos[1]=rs.getString(2);
               datos[2]=rs.getString(3);
               datos[3]=rs.getString(4);
               datos[4]=rs.getString(5);              
              
            } 
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de consulta:" + e.getMessage());
        }
        
        return datos;
    }
    
}




