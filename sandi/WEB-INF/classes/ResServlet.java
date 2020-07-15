//TimeLine.jspから入力された情報をResDBServletを通し、res_table表に保管する
//ThreadDBServletを通し、res_table表からデータを取得し、TimeLine.jspに出力する
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.List;
import database.ResDataBase;
import info.ResListProfile;
import database.ThreadDataBase;
import info.ThreadListProfile;
import info.Conversion;

public class ResServlet extends HttpServlet{
  Conversion con=new Conversion();
  public void init(ServletConfig config) throws ServletException{
		super.init(config);
		Integer count = 0;
		ServletContext application = config.getServletContext();
		application.setAttribute("count", count);
		System.out.println("ini()が実行されました");
	}

  //insert用
  public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
    ResDataBase resdata = new ResDataBase();
    ThreadDataBase thredata=new ThreadDataBase();

    //request内で使ってる文字コードの設定
    req.setCharacterEncoding("UTF-8");

    //thread_id,name,textを受け取りたい
    //thread_nameはthread名を表示するため
    String thread_id=req.getParameter("thread_id");
    String name=req.getParameter("name");
    String text=req.getParameter("text");
    String thread_name = con.conversionText(req.getParameter("thread_name"));

    //ResDataBaseにthread_id,name,textを送る
    String id=resdata.createRes(thread_id,name,text);

    //listインターフェースにResDataBaseを経由してres_table表の値を代入する
    List<ResListProfile> rlist = resdata.getRes(thread_id);
    List<ThreadListProfile> tlist = thredata.getThreadDescription(thread_id);

    //res_table表が入ったlistと、threadの情報をrequestにset
    req.setAttribute("thread_id",thread_id);
    req.setAttribute("thread_name",thread_name);
    req.setAttribute("rusers",rlist);
    req.setAttribute("tusers",tlist);

    //送り先の指定（jspのactionと同じ）
    RequestDispatcher dis=req.getRequestDispatcher("timeline");
    dis.forward(req,res);
  }

  //select用
  public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
    ResDataBase resdata=new ResDataBase();
    ThreadDataBase thredata=new ThreadDataBase();
    Conversion con=new Conversion();

    ServletContext application = this.getServletContext();
    Integer count = (Integer)application.getAttribute("count");
    count++;
    application.setAttribute("count", count);

    //request内で使ってる文字コードの設定
    req.setCharacterEncoding("UTF-8");

    //urlパラメータのthread_id,nameを受け取りたい
    //thread_nameはthread名を表示するため
    String thread_id = req.getParameter("id");
    String thread_name = con.conversionText(req.getParameter("name"));
    Integer thread_count = Integer.parseInt(req.getParameter("count"));

    System.out.println("thread_count:"+thread_count);

    //System.out.println(count);
    //listインターフェースにResDataBaseを経由してres_table表の値を代入する
    List<ResListProfile> rlist = resdata.getRes(thread_id);
    List<ThreadListProfile> tlist = thredata.getThreadDescription(thread_id);
    Integer nandemoii = thredata.updateThread(thread_id, thread_count);

    req.setAttribute("thread_id",thread_id);
    req.setAttribute("thread_name",thread_name);
    req.setAttribute("rusers",rlist);
    req.setAttribute("tusers",tlist);

    //res_table表が入ったlistと、threadの情報をrequestにset
    RequestDispatcher dis= req.getRequestDispatcher("timeline");

    dis.forward(req,res);
  }
}
