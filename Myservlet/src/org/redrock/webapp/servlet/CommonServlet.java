package org.redrock.webapp.servlet;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CommonServlet")
public class CommonServlet extends HttpServlet {
    protected Configuration configuration;

    private ThreadLocal<Map> data;

    @Override
    public void init() {
        data = new ThreadLocal<>();
        configuration = new Configuration(Configuration.VERSION_2_3_22);
        configuration.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/views");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.DEBUG_HANDLER);
    }
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map params = new HashMap();
        data.set(params);
        super.service(request, response);
    }

    protected void assign(String name, Object value) {
        data.get().put(name, value);
    }

    protected void dispaly(HttpServletResponse response, String path) throws IOException{
        Writer writer = response.getWriter();
        Template template = configuration.getTemplate(path);
        try {
            template.process(data.get(), writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }
}
