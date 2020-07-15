//CheckThread.jspから入力された情報をThreadDBServletを通し、thread_table表に保管する
//ThreadDBServletを通し、thread_table表からデータを取得し、top.jspに出力する
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import database.ThreadDataBase;
import info.ThreadListProfile;
import info.Conversion;

public class ThreadServlet extends HttpServlet{
  //insert用
  public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
    ThreadDataBase thre = new ThreadDataBase();
    Conversion con=new Conversion();
    //request内で使ってる文字コードの設定
    req.setCharacterEncoding("UTF-8");

    //title,description,themeを受け取る
    String title=con.conversionText(req.getParameter("title"));
    String description=con.conversionText(req.getParameter("description"));
    String theme=req.getParameter("theme");

    //databaseにinsertするにあたって数字に変換する
    if(theme.equals("雑談"))
      theme="2";
    if(theme.equals("相談"))
      theme="3";
    if(theme.equals("趣味"))
      theme="4";

   //ThreadDatabaseにtitle,description,themeを送る。
    String id = thre.CreateThread(title,description,theme);

    //送り先の指定（jspのactionと同じ）
    RequestDispatcher dis=req.getRequestDispatcher("completionthread");
    //実際に送る
    dis.forward(req,res);
}

  //select用
  public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{

   //request内で使ってる文字コードの設定
    req.setCharacterEncoding("UTF-8");

    //title,description,themeを受け取る
    String id = req.getParameter("id");
    String theme = req.getParameter("theme");

    //listインターフェースにThreadDataBaseを経由してthread_table表の値を代入する
    List<ThreadListProfile> famousList = ThreadDataBase.getFamousThread(theme);
    List<ThreadListProfile> newList = ThreadDataBase.getNewThread(theme);

    //res_table表が入ったlistと、thread_idをrequestにset
    req.setAttribute("id",id);
    req.setAttribute("famousUsers",famousList);
    req.setAttribute("newUsers",newList);

    //送り先の指定（jspのactionと同じ）
    RequestDispatcher dis= req.getRequestDispatcher("top");
    //実際に送る
    dis.forward(req,res);
  }
}
