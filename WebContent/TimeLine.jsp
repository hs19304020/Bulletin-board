<!-- スレッドの詳細ページ
   ResServletからデータを取得する -->
<%@ page pageEncoding="UTF-8"
 contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="ja">

<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" media="all" href="css/timeline.css">
  <title>SANちゃんねる</title>
</head>

<body>
  <div id="wrapper">
    <div id="header">
      <a href="/sandi/thread?theme=1"><img src="images/rogo1.png" width="40%"></a>
    </div>
    <div id="main">
      <br><br>

      <c:forEach items="${tusers}" var="prof1">
        <div class="thread">
          <div class="threadBox">
            <h2>${prof1.name}</h2>
            <div class="newLine">
            <p>${prof1.description}</p>
            </div>
          </div>
        </div><br><br>
      </c:forEach>

      <c:forEach items="${rusers}" var="prof">
        <div class="res">
          <div class="resBox">
            <div class="matome">
              <p>
                No.${prof.res_no}　
                ${prof.name} さん</p>
              <h6>${prof.date}</h6>
            </div>
            
            <%-- 概要の文字数が50以上のとき、省略 --%>
						<c:if test="${fn:length(prof.text) >= 50}" >
              <div class="readmore">
                <input id="${prof.res_no}" class="readmore-check" type="checkbox">
                <div class="readmore-content">
                  <div class="newLine">
                  <p>${prof.text}</p>
                  </div>

                </div>
                <label class="readmore-label" for="${prof.res_no}"></label>
              </div>
            </c:if>

            <%-- 概要の文字数が50以下のとき、そのまま --%>
            <c:if test="${fn:length(prof.text) < 50}" >
              <div class="newLine">
                <p>${prof.text}</p>
              </div>
            </c:if>


          </div>
        </div><br><br>
      </c:forEach>

      <form method="POST" action="res"><br>
        <div class="number">
          <input type="hidden" name="thread_id" value="${thread_id}">
          <input type="hidden" name="thread_name" value="${thread_name}">${thread_name}
        </div>
        <div class="names">
          <input type="text" name="name" size="90" maxlength="20" placeholder="名前を入力 ※20文字まで" required><br>
        </div>
        <div class="text"><br>
          <textarea name="text" cols="93" rows="11" placeholder="コメントを書く ※500文字まで" maxlength="500"
            required></textarea><br>
        </div>
        <div class="resbutton">
          <input type="submit" name="button" value="確認する">
        </div>
      </form>

    </div>
  </div>
</body>

</html>