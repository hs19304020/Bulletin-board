//Oracleに接続する処理を書く
package database;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import info.ResListProfile;
import info.Conversion;

public class ResDataBase{
  public static Connection set(){
    try{
      Class.forName("oracle.jdbc.driver.OracleDriver");

      //Oracleに接続する
      Connection cn=
      DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
      "sandi","pass");
      System.out.println("接続完了");

      return cn;
    }catch(ClassNotFoundException e){
      e.printStackTrace();
      System.out.println("クラスがないみたい。");
    }catch(SQLException e){
      e.printStackTrace();
      System.out.println("SQL関連の例外みたい。");
    }catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }

  public static String createRes(String thread_id, String name, String text){
    String id=null; String no="null"; String date=null;

    try{
      Class.forName("oracle.jdbc.driver.OracleDriver");

      //Oracleに接続する
      Connection cn=
      DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
      "sandi","pass");
      System.out.println("接続完了");

      //dateに今日の日付を代入
      // Calendar cal=Calendar.getInstance();
      // Date d=cal.getTime();
      // SimpleDateFormat today=new SimpleDateFormat("yy-MM-dd HH:mm:ss");
      // date=today.format(d);

      //id取得
      String getid="SELECT MAX(res_id)+1 FROM res_table";
      Statement stt1=cn.createStatement();
      ResultSet rst1=stt1.executeQuery(getid);

      while(rst1.next()){
        id = rst1.getString(1);
      }
      stt1.close();

      if(id==null)id="1";

      //レス番取得
      String getno="SELECT MAX(res_no)+1 FROM res_table WHERE thread_id="+thread_id;
      Statement stt2=cn.createStatement();
      ResultSet rst2=stt2.executeQuery(getno);

      while(rst2.next()){
        no = rst2.getString(1);
      }
      stt2.close();

      if(no==null)no="1";

      // System.out.println("id="+id+",no="+no+",thread_id="+thread_id+",name="+name+",date="+date+",text="+text);

      //insert文
      String insert="insert into RES_TABLE values('"+id+"','"+no+"','"+thread_id+"','"+name+"',sysdate,'"+text+"')";
      // String insert="insert into RES_TABLE values('"+id+"',0,'"+thread_id+"',name,sysdate,text)";
      // String insert="insert into RES_TABLE values(1, 1, 1,'res_name_1',sysdate,'text_1')";
      //Statementインターフェイスを実装するクラスをインスタンス化する
      Statement st=cn.createStatement();
      //insert文を実行し
      //ResultSetインターフェイスを実装したクラスの
      //インスタンスが返る
      ResultSet rs=st.executeQuery(insert);
      System.out.println("INSERT完了！");

      //Oracleから切断する
      cn.close();
      System.out.println("切断完了");

    }catch(ClassNotFoundException e){
      e.printStackTrace();
      System.out.println("クラスがないみたい。");
    }catch(SQLException e){
      e.printStackTrace();
      System.out.println("SQL関連の例外みたい。");
    }catch(Exception e){
      e.printStackTrace();
    }

    return id;
  }

  public static List<ResListProfile> getRes(String thread_id){
    Conversion con=new Conversion();
    //resListServletに返すためのリスト
		List<ResListProfile> resList = new ArrayList<ResListProfile>();
    String id=null;
    //String thread_id=null;
    String res_no=null; String name=null; String date=null; String text=null;
    try{
      Class.forName("oracle.jdbc.driver.OracleDriver");

      //Oracleに接続する
      Connection cn=
      DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
      "sandi","pass");
      System.out.println("接続完了");

      // String getthred="SELECT thread_id, thread_name, thread_theme, thread_date, thread_description, thread_count, FROM thread_table WHERE thread_id="+id;
      String getres="SELECT res_id, res_no, thread_id, res_user_name, to_char(res_date), res_text FROM res_table WHERE thread_id='"+thread_id+"' ORDER BY res_id ";
      Statement stt2=cn.createStatement();
      ResultSet rst2=stt2.executeQuery(getres);

      while(rst2.next()){
				ResListProfile prof = new ResListProfile();

        id=rst2.getString(1);
        res_no=rst2.getString(2);
        thread_id=rst2.getString(3);
        name=con.conversionText(rst2.getString(4));
        date=rst2.getString(5);
        text=con.conversionText(rst2.getString(6));

        prof.setId(id);
        prof.setRes_no(res_no);
        prof.setThread_id(thread_id);
        prof.setName(name);
        prof.setDate(date);
        prof.setText(text);
        resList.add(prof);
      }

      stt2.close();
      // テスト用
      // System.out.println("id="+id+",res_no:"+res_no+",thread_id:"+thread_id+",name:"+name+",date:"+date+",text:"+text);

      cn.close();
			System.out.println("切断完了");

    }catch(ClassNotFoundException e){
      e.printStackTrace();
      System.out.println("クラスがないみたい。");
    }catch(SQLException e){
      e.printStackTrace();
      System.out.println("SQL関連の例外みたい。");
    }catch(Exception e){
      e.printStackTrace();
    }

    System.out.println("resList"+resList);

    return resList;
  }
}
