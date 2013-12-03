<html>
<head>
    <title>Solr Search</title>
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
                $('#results').append($('<p><a href="MusicServlet?q=' + escape(item.artist_name) + '&l=' + $('#limit').val() + '">' + item.artist_name + '</a>'));
            });
            
        solr_searches++;
        $('#summary').empty();
        $('#summary').append("<h3>Real-time Solr searches: "+solr_searches+"</h3>");

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
<center>
<P>
<img src="http://www.datastax.com/wp-content/themes/datastax-2013/images/common/logo.png">
<P>
<table><tr><td><img src="music.jpg"></td><td><img src="music3.jpg"></td></tr></table>

<P>Type your favorite artist below to discover new music!
<P>
    Artist: <input id="query" /> # <input id="limit" value="10" size="5" />
    <button id="search">Search</button>
    <hr/>
    <div id="summary"></div>
    <div id="results"></div>
</center>    
</body>
</html>