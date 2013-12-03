<html>
<head>
    <title>Upload Playlist</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script>
    
    function add_input(){
    	$('#artisttable').append('<tr><td><input name="artist"/></td></tr>');
    }
    
    function check_inputs(){
    	var check=1;
    	$('#artistform *').filter(':input').each(function(){
    		 if($(this).val() === "")
    			    check=0;
    	});
    	if (check==1)
    		add_input();
    }

    function on_ready() {
        $('#artistform').change(add_input);
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

<p><h4>Enter the artists in your playlist</h4></p>

<form id="artistform" action="MusicServlet" method="post">
<table id="artisttable">
<tr><td><input name="artist"/></td></tr>
</table> 
<hr>
<input type="submit">
</form>

<p>&nbsp;</p>
<h3><% out.print(((request.getAttribute("msg")==null) ? "" : request.getAttribute("msg")));%></h3>
</center>    
</body>
</html>