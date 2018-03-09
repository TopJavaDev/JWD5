<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <h2>Welcome!</h2>
    <h3>Select xml parser type:</h3>
    <form action="parseXml" method="get">
      <label>
        <input type="radio" name="parserType" value="SAX" checked="checked">SAX
      </label>
      <label>
        <input type="radio" name="parserType" value="StAX">StAX
      </label>
      <label>
        <input type="radio" name="parserType" value="DOM">DOM
      </label>
      <br>
      <br>
      <input type="hidden" name="pageNumber" value="1">
      <input type="submit" value="submit">
    </form>
  </body>
</html>
