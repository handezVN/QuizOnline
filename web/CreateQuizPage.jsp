<%-- 
    Document   : CreateQuizPage
    Created on : Jan 21, 2021, 6:27:38 PM
    Author     : handez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <style>
            .text{
                width: 50%;
            }
            .Point{
                width: 30px;
            }
        </style>
    </head>
    <body>
        <form action="CreateQuizServlet"> 
            <input type="hidden" name="subjectid" value="${subjectid}">
          Point:<input type="number" name="point" required="" class="Point"><br>
          Question Context:  <br><textarea type="text" name="question" value="" required="" rows="6" cols="100%"></textarea><br>
        Answer Context:
        <div class="container1">
            <button class="add_form_field">Add New Field &nbsp; 
              <span style="font-size:16px; font-weight:bold;">+ </span>
            </button>

            <div><input type="text" name="mytext[]" class="text"> <input type="checkbox" name="selected" value="0"></div>
            <div><input type="text" name="mytext[]" class="text"> <input type="checkbox" name="selected" value="1"></div>
        </div>  
          <input type="Submit" value="Submit">
      </form>
         <script>
          $(document).ready(function() {
    var max_fields = 6;
    var wrapper = $(".container1");
    var add_button = $(".add_form_field");

    var x = 1;
    $(add_button).click(function(e) {
        e.preventDefault();
        if (x < max_fields) {
            x++;
            $(wrapper).append('<div><input type="text" name="mytext[]" class="text"/><input type="checkbox" name="selected" value="'+x+'"><a href="#" class="delete">Delete</a></div>'); //add input box
        } else {
            alert('You Reached the limits')
        }
    });

    $(wrapper).on("click", ".delete", function(e) {
        e.preventDefault();
        $(this).parent('div').remove();
        x--;
    })
});
      </script>
    </body>
</html>
