<%-- //CheckThread.jspで作成ボタンを押すと、「作成完了」的な文章が出る --%>
<%@ page pageEncoding="UTF-8"
  contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="jp">
  <head>
    <meta charset="UTF-8">
	<link rel="stylesheet" href="css/completionthread.css" media="screen">
    <title>SANちゃんねる</title>
  </head>
  <body>
    <div id="wrapper">
      <div id="header">
			<a href="/sandi/thread?theme=1"><img src="images/rogo1.png" width="40%"></a>
      </div>
      <div id="body">
        <h1>投稿が完了しました</h1>
        <a href="/sandi/thread?theme=1">
          <div class="button">
            TOPにもどる
          </div>
        </a>
      </div>
      <%-- <footer>
        <div class="footer_pagetop">
          <span>
            <a href="/sandi/thread?theme=1">PAGETOP</a>
          </span>
        </div>
        <div class="footer_copyright">
          <small>
            情報処理科１年A'班</small>
        </div>
      </footer> --%>
    </div>
  </body>
</html>
