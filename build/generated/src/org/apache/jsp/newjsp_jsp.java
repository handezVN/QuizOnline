package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class newjsp_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("         <div>Closes in <span id=\"time\">05:00</span> minutes!</div>\n");
      out.write("         <input type=\"number\" id=\"myText\" value=\"10\">\n");
      out.write("        <script>\n");
      out.write("            function startTimer(duration, display) {\n");
      out.write("    var timer = duration, minutes, seconds;\n");
      out.write("    setInterval(function () {\n");
      out.write("        minutes = parseInt(timer / 60, 10);\n");
      out.write("        seconds = parseInt(timer % 60, 10);\n");
      out.write("\n");
      out.write("        minutes = minutes < 10 ? \"0\" + minutes : minutes;\n");
      out.write("        seconds = seconds < 10 ? \"0\" + seconds : seconds;\n");
      out.write("\n");
      out.write("        display.textContent = minutes + \":\" + seconds;\n");
      out.write("\n");
      out.write("        if (--timer < 0) {\n");
      out.write("            window.location.href = \"http://stackoverflow.com\";\n");
      out.write("\n");
      out.write("        }\n");
      out.write("    }, 1000);\n");
      out.write("}\n");
      out.write("\n");
      out.write("window.onload = function () {\n");
      out.write("     var x = document.getElementById(\"myText\");\n");
      out.write("  var defaultVal = x.defaultValue;\n");
      out.write("    var fiveMinutes = defaultVal * 60,\n");
      out.write("        display = document.querySelector('#time');\n");
      out.write("    startTimer(fiveMinutes, display);\n");
      out.write("};\n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
