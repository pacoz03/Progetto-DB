import java.sql.*;

public class DBManager {
    Connection con;
    
    public DBManager()
    {
        con = null;
    }

    public boolean connect()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql//localhost:2206/campionato";
            String username = "root", psw = "root";
            con = DriverManager.getConnection(url,username,psw);
        }
        catch(Exception e)
        {
            System.out.println("Connessione al database fallita");
            return false;
        }
        return true;
    }

    public ResultSet pushQuery(String query)
    {
        try{
            PreparedStatement pquery = con.prepareStatement(query);
            ResultSet result = pquery.executeQuery();
            return result;
        }
        catch(Exception e)
        {

        }
        return null;
    }

    public void printResultSet(ResultSet rs, int ncolums)
    {
        String []out = new String[ncolums];
        try{
            while(rs.next())
            {
                for(int i = 0; i < ncolums; i++)
                {
                    out[i] = rs.getString(i);
                    System.out.print(out[i] + " ");
                }
                System.out.println();
            }
        }catch(Exception e)
        {
            System.out.println("Errore nella stampa dei risultati");
        }
    }
}
