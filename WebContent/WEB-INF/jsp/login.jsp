<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<?xml version="1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<script type="text/javascript">
</script>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta http-equiv="content-language" content="cs" />
    <meta name="robots" content="all,follow" />

    <meta name="author" content="Brandon Halpin" />
    <meta name="copyright" content="" />
    
    <title>OpenLibrary</title>
    <meta name="description" content="Welcome to OpenLibrary!" />
    <meta name="keywords" content="..." />
    
    <link rel="index" href="./" title="Home" />
    <link rel="stylesheet" media="screen,projection" type="text/css" href="./css/main.css" />
    <link rel="stylesheet" media="print" type="text/css" href="./css/print.css" />
    <link rel="stylesheet" media="aural" type="text/css" href="./css/aural.css" />
</head>

<body id="www-url-cz">

<!-- Main -->
<div id="main" class="box">

    <!-- Header -->
    <div id="header">

        <!-- Logotyp -->
        <h1 id="logo"><a href="./" title="OpenLibrary Main Page">Open<strong>Library</strong><span></span></a></h1>
        <hr class="noscreen" />          

        <!-- Quick links -->
        <div class="noscreen noprint">
            <p><em>Quick links: <a href="#content">content</a>, <a href="#tabs">navigation</a>, <a href="#search">search</a>.</em></p>
            <hr />
        </div>

        <!-- Search -->
        <div id="search" class="noprint">
            <form action="" method="get">
                <fieldset><legend>Search</legend>
                    <label><span class="noscreen">Find:</span>
                    <span id="search-input-out"><input type="text" name="" id="search-input" size="30" /></span></label>
                    <input type="image" src="design/search_submit.gif" id="search-submit" value="OK" />
                </fieldset>
            </form>
        </div> <!-- /search -->

    </div> <!-- /header -->

     <!-- Main menu (tabs) -->
     <div id="tabs" class="noprint">

            <h3 class="noscreen">Navigation</h3>
            <ul class="box">
                <li><a href="index.jsp">Home<span class="tab-l"></span><span class="tab-r"></span></a></li>
                <li><a href="addbook.htm">Add Book<span class="tab-l"></span><span class="tab-r"></span></a></li>
                <li><a href="booksowned.htm">My Books<span class="tab-l"></span><span class="tab-r"></span></a></li>
                <li><a href="reviews.htm">Reviews<span class="tab-l"></span><span class="tab-r"></span></a></li>
                <li><a href="booklist.htm">Browse Library<span class="tab-l"></span><span class="tab-r"></span></a></li>
                <li><a href="community.html">Community<span class="tab-l"></span><span class="tab-r"></span></a></li>
                <li id="active"><a href="login.htm">Login<span class="tab-l"></span><span class="tab-r"></span></a></li>
            </ul>

        <hr class="noscreen" />
     </div> <!-- /tabs -->

    <!-- Page (2 columns) -->
    <div id="page" class="box">
    <div id="page-in" class="box">

        <div id="strip" class="box noprint">

            <!-- RSS feeds -->
            <p id="rss"><strong>RSS:</strong> <a href="#">articles</a> / <a href="#">comments</a></p>
            <hr class="noscreen" />

            <!-- Breadcrumbs -->
            <p id="breadcrumbs">You are here: <strong>Login</strong> <!--&gt;  <a href="#">Category</a> &gt;  <strong>Page</strong> --></p>
            <hr class="noscreen" />
            
        </div> <!-- /strip -->

        <!-- Content -->
		<form method="post">
			<div id="strip" class="box noprint">
				<table cellpadding="0" cellspacing="0">
					
									<spring:bind
											path="command.*">
											<c:if test="${not empty status.errorMessages}">
												<c:forEach var="error" items="${status.errorMessages}">
													 <c:out value="${error}"
														escapeXml="false" />
													<br />
												</c:forEach>
											</c:if>
										</spring:bind> <!-- status messages --> <c:if test="${not empty message}">
											<c:out value="${message}" />
											<c:set var="message" value="" scope="session" />
										</c:if>

									
											<tr>
											<td>
        									<strong>Please log in! <br></br>Or click <a href="createaccount.htm">here</a> to create an account!</strong>
        									</td>
        									</tr>
        									<tr>
        									<td>
												Username:
												<spring:bind path="command.username">
													<input name='<c:out value="${status.expression}"/>'
														value='<c:out value="${status.value}"/>' type="text"
														size="12" maxlength="12"/>
												</spring:bind>
												&nbsp;Password:
												<spring:bind path="command.password">
													<input name='<c:out value="${status.expression}"/>'
														value='<c:out value="${status.value}"/>' type="password"
														size="8" maxlength="10"/>
												</spring:bind>
												&nbsp;<input type="submit" name="Submit" value="Log In"/>
											</td>
											</tr>
										
									
										
									
				</table>
			</div>	
		</form>

        <!-- Right column -->
        <div id="col" class="noprint">
            
            
            
        </div> <!-- /col -->

    </div> <!-- /page-in -->
    </div> <!-- /page -->

    <!-- Footer -->
    <div id="footer">
        <div id="top" class="noprint"><p><span class="noscreen">Back on top</span> <a href="#header" title="Back on top ^">^<span></span></a></p></div>
        <hr class="noscreen" />
        
        <p id="createdby">created by <a href="">Brandon Halpin</a> </p>
        <p id="copyright">&copy; 2012 <a href="bhalpin47@gmail.com">Brandon Halpin</a></p>
    </div> <!-- /footer -->

</div> <!-- /main -->

</body>
</html>