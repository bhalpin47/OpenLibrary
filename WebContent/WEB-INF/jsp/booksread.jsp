<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<?xml version="1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
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

<body>

<!-- Main -->
<div id="main" class="box">

    <!-- Header -->
    <div id="header">

        <!-- Logotyp -->
        <h1 id="logo"><a href="./" title="My Books">Open<strong>Library</strong><span></span></a></h1>
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
                <li id="active"><a href="booksowned.htm">My Books<span class="tab-l"></span><span class="tab-r"></span></a></li>
                <li><a href="reviews.htm">Reviews<span class="tab-l"></span><span class="tab-r"></span></a></li>
                <li><a href="booklist.htm">Browse Library<span class="tab-l"></span><span class="tab-r"></span></a></li>
                <li><a href="commmunity.html">Community<span class="tab-l"></span><span class="tab-r"></span></a></li>
                <li><a href="logout.htm">Logout<span class="tab-l"></span><span class="tab-r"></span></a></li>
                <c:if test="${user.userCode == 'A'}">
                <li><a href="administration.htm">Admin<span class="tab-l"></span><span class="tab-r"></span></a></li>
                </c:if>                
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
            <p id="breadcrumbs">You are here: <a href="index.jsp">Home</a> &gt;  <strong>My Books</strong> </p>
            <hr class="noscreen" />
            
        </div> <!-- /strip -->

        <!-- Content -->
        <div id="content">
		<c:forEach items="${books}" var="b">
            <!-- Article -->
            <div class="article">
                <h2><span><c:out value="${b.title}" /></span></h2>
                <p class="info noprint">
                    <span class="cat"><a href="#"><c:out value="${b.author}" /></a></span><span class="noscreen">,</span>
                    <span class="user"><a href="#"><c:out value="${b.user.username}" /></a></span><span class="noscreen">,</span>
                    <span class="comments"><a href='addreview.htm?bid=<c:out value="${b.bookId}"/>'>Review this book!</a></span>
                </p>
                
                <p><c:out value="${b.description}" /></p>
				<p class="info noprint"><strong>Publisher:</strong> <c:out value="${b.publisher}" />  
				 <strong>ISBN:</strong> <c:out value="${b.isbn}" /></p>
                <p class="btn-more box noprint"><strong><a href="#">More</a></strong></p>
            </div> <!-- /article -->

            <hr class="noscreen" />
		</c:forEach>
            
        </div> <!-- /content -->

        <!-- Right column -->
        <div id="col" class="noprint">
            <div id="col-in">

                <!-- About Me -->
               <h3><span><a href="editprofile.htm">Edit Profile</a></span></h3>

                <div id="about-me">
                    <p><img src="design/tmp_photo.gif" id="me" alt="Yeah, itÂ´s me!" />
                    <strong><c:out value="${user.fName}"/> <c:out value="${user.lName}"/></strong><br />
                    <br/>
                    <a href=""><c:out value="${user.email}"/></a></p>
                    <br/>
                    <br/>
                </div> <!-- /about-me -->

                <hr class="noscreen" />

                <!-- Genre -->
                <h3 ><span>My book lists</span></h3>

                <ul id="category">
                    <li><a href="booksowned.htm">Owned</a></li> <!-- Active -->
                    <li id="category-active"><a href="booksread.htm">Read</a></li>
                    <li><a href="bookswanted.htm">Wishlist</a></li>
                </ul>

                <hr class="noscreen" />

            
            </div> <!-- /col-in -->
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