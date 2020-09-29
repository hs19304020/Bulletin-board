<%-- //最初に訪れるところThread_table表からスレッドを表示する
//カテゴリ別に表示できる（デフォルトは総合）
//新着順と人気順で並び替えできる --%>
<%@ page pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ja">

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" media="all" href="css/toppage.css">
	<title>SANちゃんねる</title>
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<a href="/sandi/thread?theme=1"><img src="images/rogo1.png" width="40%"></a>
		</div>
		<ul class="nav">
			<li class="theme"><a href="/sandi/thread?theme=1">総合</a></li>
			<li class="theme"><a href="/sandi/thread?theme=2">雑談</a></li>
			<li class="theme"><a href="/sandi/thread?theme=3">相談</a></li>
			<li class="theme"><a href="/sandi/thread?theme=4">趣味</a></li>
		</ul>

		<div id="main">

		<!-- 全体のもっと見る -->
			<div class="readmoremore">
				<input id="check2" class="readmoremore-check" type="checkbox">
				<div class="readmoremore-content">

					<div class="create">
						<a href="createthread" class="button">スレッド作成</a>
					</div>
				<div class="columnbox">
					<div class="column">
						<div class="title">
							<h2>人気掲示板</h2>
						</div>
						<div class="threadMain">
							<div class="threadBox">
								<c:forEach var="prof" items="${famousUsers}">
									<a href="/sandi/res?id=${prof.id}&name=${prof.name}&count=${prof.count}">
										<div class="titlebox">
										 <div class="newLine">
										 <h3>
										  ${prof.name}
										 </h3>
										 </div>
										<%-- 概要の文字数が50以上のとき、省略 --%>
										<%-- ${fn:length(prof.description)} --%>
										<c:if test="${fn:length(prof.description) >= 50}" >
											<!-- 概要のもっと見る -->
											<div class="readmore">
												<input id="${prof.id}" class="readmore-check" type="checkbox">
												<div class="readmore-content">
													<div class="newLine">
													${prof.description}
													</div>
													<!-- 概要の閉じる -->
												</div>
												<label class="readmore-label" for="${prof.id}"></label>
											</div>
										</c:if>

										<%-- 概要の文字数が50以下のとき、そのまま --%>
										<c:if test="${fn:length(prof.description) < 50}" >
											<div class="newLine">
												${prof.description}
											</div>
										</c:if>
										<p>テーマ : ${prof.theme}</p>
										<h6><span class="mgr-1">閲覧数 : ${prof.count}
										</span><span class="mgr-2">投稿日時 : ${prof.date}</span></h6>
									</div></a><br>
								</c:forEach>
							</div><br><br><br>
						</div>
					</div>

						<div class="column">
							<div class="title">
								<h2>新着掲示板</h2>
							</div>
						<div class="threadMain">
							<div class="threadBox">
								<c:forEach var="prof" items="${newUsers}">
									<a href="/sandi/res?id=${prof.id}&name=${prof.name}&count=${prof.count}">
										<div class="titlebox">
										 <div class="newLine">
										 <h3>
										  ${prof.name}
										 </h3>
										 </div>
										<%-- 概要の文字数が50以上のとき、省略 --%>
										<%-- ${fn:length(prof.description)} --%>
										<c:if test="${fn:length(prof.description) >= 50}" >
											<!-- 概要のもっと見る -->
											<div class="readmore">
												<input id="readmore ${prof.id}" class="readmore-check" type="checkbox">
												<div class="readmore-content">
													<div class="newLine">
														${prof.description}
													</div>
													
													<!-- 概要の閉じる -->
												</div>
												<label class="readmore-label" for="readmore ${prof.id}"></label>
											</div>
										</c:if>

										<%-- 概要の文字数が50以下のとき、そのまま --%>
										<c:if test="${fn:length(prof.description) < 50}" >
											<div class="newLine">
												${prof.description}
											</div>
										</c:if>
										<p>テーマ : ${prof.theme}</p>
										<h6><span class="mgr-1">閲覧数 : ${prof.count}
										</span><span class="mgr-2">投稿日時 : ${prof.date}</span></h6>
									</div></a><br>
								</c:forEach>

							</div><br><br><br>
						</div>
					</div>
				</div>
					<br>

					<!-- 全体の閉じる -->
				</div>
				<label class="readmoremore-label" for="check2"></label>
			</div>

		</div>
	</div>
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

</body>

</html>
