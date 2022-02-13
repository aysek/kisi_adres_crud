import entity.Kisi;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.ManagedBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScope
public class KayitCek {
    List<Kisi> sorguSonucu;

    public void setSorguSonucu(List<Kisi> sorguSonucu) {
        this.sorguSonucu = sorguSonucu;
    }
    public List<Kisi> getSorguSonucu() {
         return sorguSonucu;
    }

public List<Kisi> getTablodakiKayitlar(){
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;
    sorguSonucu=new ArrayList<>();
    try{
        Class.forName("com.msql.jdbc.Driver");
        connection= DriverManager.getConnection("jdbc:mysql://localhost:PORT/tablename","root","");
        preparedStatement=connection.prepareStatement("select*from kisiler");
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            Kisi kisi=new Kisi();
            kisi.setId(resultSet.getLong("id"));
            kisi.setAdi(resultSet.getString("adi"));
            kisi.setSoyadi(resultSet.getString("soyadi"));
            sorguSonucu.add(kisi);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    return sorguSonucu;
}





}
