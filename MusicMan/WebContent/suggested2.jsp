<html>
<head>
    <title>Suggested Artists</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script>

function generatePage(){
	get_artists();
	get_album_image();
}

function get_album_image() {
        var query = escape('<% out.print(request.getParameter("q"));%>');
        var url='BingServlet?q='+query;
        $.get(url, function(data) {
            $('#image').append('<img src="' + data + '">');
            $('#cassandra').append('<img src="cassandra.png">')
            $('#hadoop').append('<img src="hadoop.png">')
            });
}

function get_artists(){
	var docs = <% out.print(request.getAttribute("suggested_artists"));%>;
    $.each(docs, function(i, item) {
        $('#results').append($('<tr><td>'+(i+1)+'.<td><a href="MusicServlet?q=' + escape(item.suggested_artist) + '&l=<% out.print(request.getParameter("l"));%>">' + item.suggested_artist + '</a></td><td>'+item.support+'</td><td>'+item.confidence+'</td><td>'+item.lift+'</td></tr>'));
    });
}


</script>    
</head>
<body onload="generatePage()">
<center>
<P>
<a href="index.jsp">
<img src="http://www.datastax.com/wp-content/themes/datastax-2013/images/common/logo.png">
</a>
<P>
<table><tr><td><img src="music.jpg"></td><td><div id="image"></div></td></tr></table>

<P><h1>If you like <% out.print(request.getParameter("q"));%><br>You might also enjoy:
</h1>
<P>
<table><tr><td><div id="cassandra"></div></td><td>
    <table id="results">
    <tr>
    <th></th>
    <th>Artist</th>
    <th>Support</th>
    <th>Confidence</th>
    <th>Lift</th>   
    </tr>
    </table>
</td><td><div id="hadoop"></div></td></table>    
</center>    
</body>
</html>