<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>Welcome to the Music Network</title>
<link href="style.css" rel="stylesheet" type="text/css"/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script>

var solr_searches=0;

    function on_search() {
        var query = escape($('#query').val());
        if (query.length == 0) {
            return;
        }

        var url='SolrServlet';
        $.getJSON(url, 'q='+query, function(data) {
            $('#results').empty();
            var docs = data.response.docs;
            $.each(docs, function(i, item) {
                $('#results').append($('<li><a href="MusicServlet?q=' + escape(item.artist_name) + '&l=' + $('#limit').val() + '">' + item.artist_name + '</li>'));
            });
            
        solr_searches++;
        $('#summary').empty();
        $('#summary').append("<strong>Real-time Solr searches: "+solr_searches+"</strong>");

        });
    }

    function on_ready() {
        $('#search').click(on_search);
        /* Hook enter to search */
        $('#query').keyup(function() {
                on_search();
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
            <h1>Discover new music instantly</h1>
            <p><strong>The Music Network</strong> is an intelligent music search engine. Search for an artist to get new music recommendations. Or, upload your favorite play list to teach the search engine about new music!</p>

            <h1>Type your favorite artist below</h1>
            <p><strong>Artist: </strong><input id="query" /> # <input id="limit" value="10" size="5" /><button id="search">Search</button></p>
    		<hr/>
    		<p><div id="summary"></div></p>
            <ul id="results">

            </ul>
            <p><strong>Click an artist</strong> in the result list to find suggestions.</p>
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
