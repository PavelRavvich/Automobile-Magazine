<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>Add You're sale propose:</h2>

<form method="POST" enctype="multipart/form-data" action="add_propose">

    Mark: <input type="text" name="mark">
    <br/>
    Model: <input type="text" name="model">
    <br/>
    Description: <input type="text" name="description">
    <br/>
    File to upload: <input type="file" name="upfile"><br/>
    <br/>
    <input type="submit" value="Press"> to upload the file!
</form>

</body>
</html>
