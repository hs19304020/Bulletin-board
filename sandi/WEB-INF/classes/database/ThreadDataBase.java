//databaseのthread_tableに接続する処理を書く
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
import info.ThreadListProfile;
import info.Conversion;

public class ThreadDataBase{
  // public static void main(String[] args){
  //   CreateThread(args[0],args[1],args[2]);
  // }

  //thread_tableにinsertする
  public static String CreateThread(String name, String description, String theme){
    //初期化
    String id=null; String date=null;  String count="0";

    try{
      Class.forName("oracle.jdbc.driver.OracleDriver");

      //Oracleに接続する
      Connection cn=
      DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
      "sandi","pass");
      System.out.println("接続完了");

      //dateに今日の日付を代入
      Calendar cal=Calendar.getInstance();
      Date d=cal.getTime();
      SimpleDateFormat today=new SimpleDateFormat("yy-MM-dd HH:mm:ss");
      date=today.format(d);

      //thread_idに新しいidをいれる
      String getid="SELECT MAX(thread_id)+1 FROM thread_table";
      Statement stt1=cn.createStatement();
      ResultSet rst1=stt1.executeQuery(getid);
      while(rst1.next()){
        id = rst1.getString(1);
      }
      stt1.close();

      if(id==null)id="1";

      //確認用
      System.out.println("id="+id+",name="+name+",theme="+theme+",date="+date+",description="+description+",count="+count);

      //insert文
      String insert="insert into THREAD_TABLE values('"+id+"','"+name+"','"+theme+"',sysdate,'"+description+"',"+count+")";
      //Statementインターフェイスを実装するクラスをインスタンス化する
      Statement st=cn.createStatement();
      //insert文を実行し
      //ResultSetインターフェイスを実装したクラスのインスタンスが返る
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
    //ThreadServletに戻る
    return id;
  }

  //thread_tableからselectする
  public static List<ThreadListProfile> getFamousThread(String theme){
    Conversion con=new Conversion();
    //ThreadServletに返すためのリスト
		List<ThreadListProfile> threadList = new ArrayList<ThreadListProfile>();
    String id=null;
    //String theme=null;
    String name=null; String description=null; Integer count=null; String date=null;
    try{
      Class.forName("oracle.jdbc.driver.OracleDriver");

      //Oracleに接続する
      Connection cn=
      DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
      "sandi","pass");
      System.out.println("接続完了");

      //select文　カテゴリが総合の場合すべて表示、それ以外はテーマごとに表示
      String getthred;
      if(theme.equals("1")){
        // System.out.println("if:"+theme);
        getthred="SELECT thread_id, thread_name, thread_theme, to_char(thread_date,'YYYY/MM/DD HH24:MI:SS DAY'), thread_description, thread_count FROM thread_table ORDER BY thread_count desc";
      }else{
        // System.out.println("if:"+theme);
        getthred="SELECT thread_id, thread_name, thread_theme, to_char(thread_date,'YYYY/MM/DD HH24:MI:SS DAY'), thread_description, thread_count FROM thread_table WHERE thread_theme='"+theme+"' ORDER BY thread_count desc";
      }
      //Statementインターフェイスを実装するクラスをインスタンス化する
      Statement stt2=cn.createStatement();
      //select文を実行し
      //ResultSetインターフェイスを実装したクラスのインスタンスが返る
      ResultSet rst2=stt2.executeQuery(getthred);

      while(rst2.next()){
				ThreadListProfile prof = new ThreadListProfile();

        id=rst2.getString(1);
        name=rst2.getString(2);
        theme=rst2.getString(3);
        date=rst2.getString(4);
        description=rst2.getString(5);
        count=Integer.parseInt(rst2.getString(6));

        if(theme.equals("1"))
          theme="総合";
        if(theme.equals("2"))
          theme="雑談";
        if(theme.equals("3"))
          theme="相談";
        if(theme.equals("4"))
          theme="趣味";

        //beanファイルで値をsetし、listにaddする
        prof.setId(id);
        prof.setName(name);
        prof.setDescription(description);
        prof.setTheme(theme);
        prof.setCount(count);
        prof.setDate(date);
        threadList.add(prof);
      }
      //確認用
      System.out.println("id="+id+",name:"+name+",theme:"+theme+",date:"+date+",description:"+description+",count:"+count);

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
    return threadList;
  }

  public static List<ThreadListProfile> getNewThread(String theme){
    Conversion con=new Conversion();
    //ThreadServletに返すためのリスト
    List<ThreadListProfile> threadList = new ArrayList<ThreadListProfile>();
    String id=null;
    //String theme=null;
    String name=null; String description=null; Integer count=null; String date=null;
    try{
      Class.forName("oracle.jdbc.driver.OracleDriver");

      //Oracleに接続する
      Connection cn=
      DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
      "sandi","pass");
      System.out.println("接続完了");

      //select文　カテゴリが総合の場合すべて表示、それ以外はテーマごとに表示
      String getthred;
      if(theme.equals("1")){
        // System.out.println("if:"+theme);
        getthred="SELECT thread_id, thread_name, thread_theme, to_char(thread_date,'YYYY/MM/DD HH24:MI:SS DAY'), thread_description, thread_count FROM thread_table ORDER BY thread_date desc";
      }else{
        // System.out.println("if:"+theme);
        getthred="SELECT thread_id, thread_name, thread_theme, to_char(thread_date,'YYYY/MM/DD HH24:MI:SS DAY'), thread_description, thread_count FROM thread_table WHERE thread_theme='"+theme+"' ORDER BY thread_date desc";
      }
      //Statementインターフェイスを実装するクラスをインスタンス化する
      Statement stt2=cn.createStatement();
      //select文を実行し
      //ResultSetインターフェイスを実装したクラスのインスタンスが返る
      ResultSet rst2=stt2.executeQuery(getthred);

      while(rst2.next()){
        ThreadListProfile prof = new ThreadListProfile();

        id=rst2.getString(1);
        name=rst2.getString(2);
        theme=rst2.getString(3);
        date=rst2.getString(4);
        description=rst2.getString(5);
        count=Integer.parseInt(rst2.getString(6));

        if(theme.equals("1"))
          theme="総合";
        if(theme.equals("2"))
          theme="雑談";
        if(theme.equals("3"))
          theme="相談";
        if(theme.equals("4"))
          theme="趣味";

        //beanファイルで値をsetし、listにaddする
        prof.setId(id);
        prof.setName(name);
        prof.setDescription(description);
        prof.setTheme(theme);
        prof.setCount(count);
        prof.setDate(date);
        threadList.add(prof);
      }
      //確認用
      System.out.println("id="+id+",name:"+name+",theme:"+theme+",date:"+date+",description:"+description+",count:"+count);

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
    return threadList;
  }

  public static List<ThreadListProfile> getThreadDescription(String id){
    Conversion con=new Conversion();
    //ThreadServletに返すためのリスト
    List<ThreadListProfile> threadList = new ArrayList<ThreadListProfile>();
    //String theme=null;
    String name=null; String theme=null; String description=null; Integer count=null; String date=null;
    try{
      Class.forName("oracle.jdbc.driver.OracleDriver");

      //Oracleに接続する
      Connection cn=
      DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
      "sandi","pass");
      System.out.println("接続完了");

      //select文　カテゴリが総合の場合すべて表示、それ以外はテーマごとに表示
      String getthred="SELECT thread_id, thread_name, thread_theme, to_char(thread_date), thread_description, thread_count FROM thread_table WHERE thread_id="+id;

      //Statementインターフェイスを実装するクラスをインスタンス化する
      Statement stt2=cn.createStatement();
      //select文を実行し
      //ResultSetインターフェイスを実装したクラスのインスタンスが返る
      ResultSet rst2=stt2.executeQuery(getthred);

      while(rst2.next()){
        ThreadListProfile prof = new ThreadListProfile();

        id=rst2.getString(1);
        name=rst2.getString(2);
        theme=rst2.getString(3);
        date=rst2.getString(4);
        description=rst2.getString(5);
        count=Integer.parseInt(rst2.getString(6));

        if(theme.equals("1"))
          theme="総合";
        if(theme.equals("2"))
          theme="雑談";
        if(theme.equals("3"))
          theme="相談";
        if(theme.equals("4"))
          theme="趣味";

        //beanファイルで値をsetし、listにaddする
        prof.setId(id);
        prof.setName(name);
        prof.setDescription(description);
        prof.setTheme(theme);
        prof.setCount(count);
        prof.setDate(date);
        threadList.add(prof);
      }
      //確認用
      System.out.println("id="+id+",name:"+name+",theme:"+theme+",date:"+date+",description:"+description+",count:"+count);

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
    return threadList;
  }

  //ここにupdate文なお表はturead_table表
  //列はthread_count 型はnumber 8
  public static Integer updateThread(String id, Integer count){

    try{
      Class.forName("oracle.jdbc.driver.OracleDriver");

      //Oracleに接続する
      Connection cn=
      DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
      "sandi","pass");
      System.out.println("接続完了");

      String selectcount="SELECT thread_count FROM thread_table WHERE thread_id="+id;
      Statement stt1=cn.createStatement();
      ResultSet rst1=stt1.executeQuery(selectcount);

      while(rst1.next()){
        count = Integer.parseInt(rst1.getString(1));
      }
      stt1.close();

      if(count==null)id="1";

      //update文　カテゴリが総合の場合すべて表示、それ以外はテーマごとに表示
      count++;
      String update="UPDATE thread_table SET thread_count="+count+"WHERE thread_id="+id;


      //Statementインターフェイスを実装するクラスをインスタンス化する
      Statement stt2=cn.createStatement();
      //update文を実行し
      //ResultSetインターフェイスを実装したクラスのインスタンスが返る
      ResultSet rst2=stt2.executeQuery(update);


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
    return count;
  }
}
