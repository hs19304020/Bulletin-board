<%-- //CreateThread.jspで入力された情報を確認する
//作成ボタンを押すとCreateThreadServlet.javaにデータを送る --%>

<%@ page pageEncoding="UTF-8"
  contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="jp">

<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="css/checkthread.css" media="screen">
  <title>SANちゃんねる</title>
</head>

<body>
  <div id="wrapper">
    <div id="header">
      <a href="/sandi/thread?theme=1"><img src="images/rogo1.png" width="40%"></a>
    </div>

    <form method="POST" action="thread">
      <div id="body">
        <div class="kakunin">
          <h3>掲示板の作成</h3>
          <p>下記の内容でよろしいですか？</p>
          <table border="1" >
            <tr>
              <td>
                <p>タイトル：</p>
              </td>

              <td width="900px">
                <div class="title">
                  <div class="newLineti">
                  <input type="hidden" name="title" value="${title}">

                    ${title}
                  </div>
                  <br>
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <p>概要：</p>
              </td>
              <td>
                <div class="description">
                  <input type="hidden" name="description" value="${description}">
                  <div class="newLine">
                    ${description}
                  </div>
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <p>カテゴリ：</p>
              </td>
              <td>
                <div class="theme">
                  <input type="hidden" name="theme" value="${theme}">${theme}<br>
                </div>
              </td>
            </tr>
          </table>
        </div>
      </div>

      <div class="backbutton">
        <input type="button" onclick="history.back()" value="１つ前のページに戻る">
      </div>
      <div class="nextbutton">
        <input type="submit" name="" value="投稿する">
      </div>
    </form>
    <br><br><br><br>

    <footer>
      <div class="footer_pagetop">
        <span>
          <a href="/sandi/thread?theme=1">PAGETOP</a>
        </span>
      </div>
      <div class="footer_copyright">
        <small>
          情報処理科１年A'班</small>
      </div>
    </footer>
  </div>
</body>

</html>
