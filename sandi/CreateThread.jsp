<%-- //スレッドを作る、CheckThread.jspにデータを送る --%>
<%@ page pageEncoding="UTF-8"
  contentType="text/html;charset=UTF-8" %>

<html>

<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="css/createthread.css" media="screen">
  <title>PHP TEST</title>
</head>

<body>
  <div id="wrapper">
    <div id="header">
			<a href="/sandi/thread?theme=1"><img src="images/rogo1.png" width="40%"></a>
    </div>
    <form method="POST" action="create">
      <div class="title">
        <label for="text"></label><br>
        <input type="text" name="title" size="77" maxlength="50" placeholder="タイトル ※25文字まで" required>
      </div>
      <div class="description">
        <label for="textarea">
          <br>
          <textarea name="description" cols="80" rows="13" placeholder="説明文　※500文字まで" maxlength="500"></textarea>
        </label>
      </div>
      <div class="theme">
        <br>カテゴリー<br>
        <label><input type="radio" name="theme" value="雑談" required>雑談</label>
        <label><input type="radio" name="theme" value="相談" required>相談</label>
        <label><input type="radio" name="theme" value="趣味" required>趣味</label>
      </div>
      <div class="button">
        <br><br><input type="submit" name="button1" value="確認する">
        <br><br><br>
      </div>
    </form>
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
