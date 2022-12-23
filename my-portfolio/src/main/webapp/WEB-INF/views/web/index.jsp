<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="icon" type="image/x-icon"
	href="/image-file/${websiteInfo.favicon}">
<!-- google font -->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;500&display=swap"
	rel="stylesheet" />
<!-- main css -->
<link rel="stylesheet" href="/web-template/css/main.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<title>${websiteInfo.name}|${aboutMe.name}|${aboutMe.major}</title>
<style>
.card {
	display: none;
}
</style>
</head>

<body>
	<!-- overlay -->
	<section id="overlay"></section>
	<!-- header -->
	<header>
		<div class="container">
			<nav class="navbar">
				<!-- logo -->
				<div class="navbar__logo">
					<img src="/image-file/${websiteInfo.logo}"
						style="border-radius: 50%;" alt="website logo" />
				</div>
				<!-- links -->
				<ul class="navbar__links">
					<c:forEach var="menu" items="${activeMenuList}">
						<c:if test="${menu.name eq 'Resume'}">
							<a href="${resumePDFLink}" target="_blank"><button
									class="navbar__btn">${menu.name}</button></a>
						</c:if>
						<c:if test="${menu.name != 'Resume'}">
							<li class="navbar__link"><a href="#${menu.urlCode}">${menu.name}</a></li>
						</c:if>
					</c:forEach>
				</ul>
				<!-- menu button -->
				<div class="navbar__icons">
					<div class="navbar__icon"></div>
				</div>
			</nav>
		</div>
	</header>
	<!-- banner -->
	<section id="banner">
		<div class="container">
			<!-- img -->
			<div class="banner__img">
				<img src="/web-template/img/banner-img.png"
					alt="illustration of women" />
			</div>
			<!-- heading -->
			<div class="banner__heading">
				<h1>${aboutMe.name}</h1>
				<p>${aboutMe.major}</p>
				<!-- <a href="#"><button class="banner__btn">View More</button></a> -->
			</div>
		</div>
	</section>
	<!-- things -->
	<section id="about">
		<div class="container">
			<!-- dec -->
			<div class="things__dec">
				<img src="/web-template/img/things-dec-l.png"
					alt="illustration of leaf" />
			</div>
			<!-- dec -->
			<div class="things__dec"></div>
			<!-- heading -->
			<h2>About Me</h2>
			<!-- item 1 -->
			<div class="things__item">
				<!-- img -->
				<img src="/web-template/img/things-item-1.png"
					alt="illustration of women" />
				<!-- text -->
				<div class="things__item--heading">
					<h3 style="margin-top: 1.3em;">Introduction</h3>
					<p>Hello! My name is Duy and I'm a fresh graduate of the T3H
						Information Technology Institute with a professional programmer.
						I'm interested in creating websites, applications, and software to
						support people's use of the internet effectively in life and work.
					</p>
					<p>
						Here are a few technologies I've been working with recently: <span
							style="color: darkgreen; font-weight: 450;"> <c:forEach
								var="lat" items="${activeLTList}">
								${lat.name} | 
							</c:forEach></span>
					</p>
				</div>
			</div>
			<!-- item 2 -->
			<div class="things__item">
				<!-- img -->
				<img src="/web-template/img/things-item-2.png"
					alt="illustration of women" />
				<!-- text -->
				<div class="things__item--heading">
					<h3>Career Goals</h3>
					<p>I want to become a Senior Developer supporting the company,
						and businesses to create and complete many useful, friendly
						products for customers.</p>
				</div>
			</div>
			<!-- item 3 -->
			<div class="things__item">
				<!-- img -->
				<img src="/web-template/img/things-item-3.png"
					alt="illustration of computer" />
				<!-- text -->
				<div class="things__item--heading">
					<h3>Certificates</h3>
					<c:forEach var="cer" items="${activeCertificateList}">
						<fmt:formatDate value="${cer.time}" pattern="dd/MM/yyyy"
							var="time" />
						<p>
							${time}: <span style="color: darkgreen; font-weight: 450;">${cer.name}</span>
						</p>
					</c:forEach>
				</div>
			</div>
		</div>
	</section>
	<!-- works -->
	<section id="experience">
		<div class="container">
			<!-- heading -->
			<h2>What I've Worked?</h2>
		</div>
	</section>
	<section class="card-container">
		<div class="container">
			<c:forEach var="project" items="${activeProjectList}" begin="0"
				step="1" varStatus="i">
				<div class="card">
					<div class="box">
						<div class="content">
							<h2>${i.count}</h2>
							<h3>${project.name}</h3>
							<fmt:formatDate value="${project.startDate}" pattern="dd/MM/yyyy"
								var="startDate" />
							<c:if test="${project.endDate == null}">
								<c:set var="endDate" value="Present"></c:set>
							</c:if>
							<c:if test="${project.endDate != null}">
								<fmt:formatDate value="${project.endDate}" pattern="dd/MM/yyyy"
									var="endDate" />
							</c:if>
							<p>
								<small>${startDate} - ${endDate}</small>
							</p>
							<p>${project.shortDesc}</p>
							<br>
							<p>
								<c:forEach var="lat" items="${project.languagesAndTechnologies}">
									<small style="font-weight: 450;">${lat.name} | </small>
								</c:forEach>
							</p>
							<c:if test="${project.github != null}">
								<a href="${project.github}"><i class="fab fa-github"></i></a>
							</c:if>
							<c:if test="${project.deployLink != ''}">
								<a href="${project.deployLink}">Visit site</a>
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- btn -->
		<div class="works__btn" style="text-align: center;">
			<button class="loadMoreBtn">View Works</button>
		</div>
	</section>
	<!-- footer -->
	<footer id="contact">
		<div class="container">
			<!-- dec -->
			<div class="footer__dec">
				<img src="/web-template/img/things-dec-l.png"
					alt="illustration of leaf" />
			</div>
			<!-- img -->
			<div class="footer__img">
				<img src="/web-template/img/footer-img.png"
					alt="illustration of women" />
			</div>
			<!-- heading -->
			<div class="footer__heading">
				<h1>Contact With Me!</h1>
				<!-- contact -->
				<div class="footer__contact">
					<a href="tel:0367727951"> <i class="fas fa-phone-alt"></i> <span>${aboutMe.phone}</span>
					</a> <a href="mailto:${aboutMe.email}"> <i class="fas fa-envelope"></i>
						<span>${aboutMe.email}</span>
					</a>
				</div>
				<div class="banner__socials">
					<a href="${aboutMe.github}"><i class="fab fa-github"></i></a> <a
						href="${aboutMe.facebook}"><i class="fab fa-facebook-f"></i></a> <a
						href="${aboutMe.instagram}"><i class="fab fa-instagram"></i></a> <a
						href="${aboutMe.linkedIn}"><i class="fab fa-linkedin-in"></i></a>
				</div>
			</div>
		</div>
	</footer>
	<!-- fontawesome -->
	<script src="https://kit.fontawesome.com/28c0af3030.js"
		crossorigin="anonymous"></script>
	<!-- main js -->
	<script src="/web-template/js/main.js"></script>
	<script>
		$(".card").slice(0, 3).show();
		$(".loadMoreBtn").on("click", function() {
			$(".card:hidden").slice(0, 3).show();
			if ($(".card:hidden").length == 0) {
				$(".loadMoreBtn").fadeOut();
			}
		});
	</script>
</body>

</html>