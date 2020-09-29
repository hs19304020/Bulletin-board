//CreateThread.jsp������͂��ꂽ����checkThread.jsp�ɕ\������
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
   try {
	   //request���Ŏg���Ă镶���R�[�h�̐ݒ�
	   req.setCharacterEncoding("UTF-8");

	   //CreateThread�œ��͂��ꂽtitle,description,theme���󂯎��
	   String title=con.conversionTextNotbr(req.getParameter("title"));
	   String description=con.conversionTextNotbr(req.getParameter("description"));
	   String theme=req.getParameter("theme");

	   //title,description,theme��request��set
	   req.setAttribute("title",title);
	   req.setAttribute("description",description);
	   req.setAttribute("theme",theme);

	   int a = count(title);

	  if(a==title.length()){
		  throw new SpaceOnlyTrace("�󔒂����͂��߂���");
	  }else{
	    //�����̎w��ijsp��action�Ɠ����j
	    RequestDispatcher dis=req.getRequestDispatcher("checkthread");
	    //���ۂɑ���
	    dis.forward(req,res);
	  }
   }catch(SpqceOnlyException e) {
	   System.out.println(e.getMessage());
	   System.out.println("a");
	   RequestDispatcher dis=req.getRequestDispatcher("createthread");
	   dis.forward(req,res);
   }
 }

 public int count(String name){
  char someChar1 = ' ';
  char someChar2 = '�@';
  int count = 0;

  for (int i = 0; i < name.length(); i++) {
    if (name.charAt(i) == someChar1 || name.charAt(i) == someChar2) {
           count++;
    }
  }
   // assertEquals(2, count);
   System.out.println("������"+count);
   return count;
 }
}
