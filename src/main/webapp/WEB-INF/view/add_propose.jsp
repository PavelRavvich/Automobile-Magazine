<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>Add You're sale propose:</h2>

    <form method="post" action="add_propose">

        <input type="text" name="mark">
        <input type="text" name="model">
        <input type="text" name="description">


        <input type="submit" value="Send">

    </form>

<form method="POST" enctype="multipart/form-data" action="add_propose">
    File to upload: <input type="file" name="upfile"><br/>
    Notes about the file: <input type="text" name="note"><br/>
    <br/>
    <input type="submit" value="Press"> to upload the file!
</form>

</body>
</html>
