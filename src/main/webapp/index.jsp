<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <h2>Welcome!</h2>
    <h3>Select xml by.tc.task05.parser type:</h3>
    <form action="parseXml" method="get">
      <label>
        <input type="radio" name="parserType" value="SAX">SAX
      </label>
      <label>
        <input type="radio" name="parserType" value="StAX">StAX
      </label>
      <label>
        <input type="radio" name="parserType" value="DOM">DOM
      </label>
      <br>
      <input type="submit" value="submit">
    </form>
  </body>
</html>
