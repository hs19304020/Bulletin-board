//CreateThread.jspから入力された情報をcheckThread.jspに表示する
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import info.Conversion;

public class CreateThreadServlet extends HttpServlet{
 public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
    Conversion con=new Conversion();
   //request内で使ってる文字コードの設定
   req.setCharacterEncoding("UTF-8");

   //CreateThreadで入力されたtitle,description,themeを受け取る
   String title=con.conversionTextNotbr(req.getParameter("title"));
   String description=con.conversionTextNotbr(req.getParameter("description"));
   String theme=req.getParameter("theme");

   //title,description,themeをrequestにset
   req.setAttribute("title",title);
   req.setAttribute("description",description);
   req.setAttribute("theme",theme);

   int a = count(title);

  if(a==title.length()){
    //送り先の指定（jspのactionと同じ）
    RequestDispatcher dis=req.getRequestDispatcher("createthread");
    //実際に送る
    dis.forward(req,res);
  }else{
    //送り先の指定（jspのactionと同じ）
    RequestDispatcher dis=req.getRequestDispatcher("checkthread");
    //実際に送る
    dis.forward(req,res);
  }
 }

 public int count(String name){
  char someChar1 = ' ';
  char someChar2 = '　';
  int count = 0;

  for (int i = 0; i < name.length(); i++) {
    if (name.charAt(i) == someChar1 || name.charAt(i) == someChar2) {
           count++;
    }
  }
   // assertEquals(2, count);
   System.out.println("文字数"+count);
   return count;
 }
}
