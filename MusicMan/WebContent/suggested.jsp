<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>Music Network Suggestions</title>
<link href="style.css" rel="stylesheet" type="text/css"/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script>

function on_ready(){
	get_artists();
	get_album_image();
}

function get_album_image() {
        var query = escape('<% out.print(request.getParameter("q"));%>');
        var url='BingServlet?q='+query;
        $.get(url, function(data) {
            $('#image').append('<img src="' + data + '">');
            });
}

function get_artists(){
	var docs = <% out.print(request.getAttribute("suggested_artists"));%>;
    $.each(docs, function(i, item) {
        $('#results').append($('<tr><td>'+(i+1)+'.<td><a href="MusicServlet?q=' + escape(item.suggested_artist) + '&l=<% out.print(request.getParameter("l"));%>">' + item.suggested_artist + '</a></td><td>'+item.support+'</td><td>'+item.confidence+'</td><td>'+item.lift+'</td></tr>'));
    });
}

$(document).ready(on_ready);

</script>  
</head>
<body>
<div id="container">
    	<!-- header -->
        <div id="logo"><a href="#">The Music Network</a></div>
        <div id="menu">
            <a href="index.jsp">search</a>
            <a href="uploadplaylist.jsp">upload playlist</a>
            <a href="howitworks.jsp">how it works</a>
        </div>
        <!--end header -->     
        <!-- main -->
        <div id="main">
          <div id="sidebar">
          	<h2>Powered By DSE</h2>
          	<img src="images/ds_logo1.png">
            <ul>
              <li><a href="http://www.datastax.com/big-data-challenges">Big Data Challenges</a></li>
              <li><a href="http://www.datastax.com/customers">Customers</a></li>
              <li><a href="http://www.datastax.com/what-we-offer/products-services">What We Offer</a></li>
              <li><a href="http://www.datastax.com/dev">Developer Center</a></li>
              <li><a href="http://www.datastax.com/company">Company</a></li>
            </ul>
          </div>
          <div id="text">
            <h1>Recommended Artists</h1>
            
            <p><div id="image"></div></p>
            
            <p>If you like <strong><% out.print(request.getParameter("q"));%></strong>, you might also enjoy:</p>
			<hr/>
			
			<p>
    		<table id="results">
    		<tr>
    		<th></th>
    		<th>Artist</th>
    		<th>Support</th>
    		<th>Confidence</th>
    		<th>Lift</th>   
    		</tr>
    		</table>  
			</p>
			
            <p><strong>Click an artist name</strong> to discover even more music.</p>
            
            <p id="info">The Music Network is a community-driven music recommendation engine. Based on your selected artist, we use playlist data from other users to find relationships between artists. Using these relationships, we can make recommendations to help you discover new artists.</p>
            
           </div>
    </div>
    <!-- end main -->
    <!-- footer -->
    <div id="footer">
            <div id="menu_footer"><a href="index.jsp">search</a> | <a href="uploadplaylist.jsp">upload playlist</a> | <a href="howitworks.jsp">how it works</a> </div>
            <div id="left_footer">&copy; Copyright 2013 <b>The Music Network</b></div>
            <div id="right_footer">

<!-- Please do not change or delete these links. Read the license! Thanks. :-) -->
<a href="http://www.realitysoftware.ca/services/website-development/design/"><strong>Web design</strong></a> released by <a href="http://www.flashmp3player.org/"><strong>Flash MP3 Player</strong></a>, Courtesy <a href="http://www.openwebdesign.org" target="_blank">Open Web 
Design</a>, Thanks to <a href="http://www.seo.us.com" target="_blank">Search Engine Optimization</a>

   		</div>
	</div>
    <!-- end footer -->
</div>
</body>
</html>
