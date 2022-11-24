package br.com.voltorb.sdoc_java;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import br.com.voltorb.sdoc_java.model.Movie;

public class HTMLGenerator {

    private Writer writer;

    public HTMLGenerator(Writer writer) {
        this.writer = writer;
    }

    public void generate(List<Movie> movies) throws IOException {
        StringBuilder html = new StringBuilder("<!DOCTYPE html><html>");

        String head = """
                <head>
                	<meta charset=\"utf-8\">
                	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
                	<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\"
                		+ integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">
                </head>
                """;

        StringBuilder body = new StringBuilder("<body> <div class=\"row mt-5 ml-3\">");
        for (Movie movie : movies) {
            String divTemplate = """
                    <div class=\"card text-white bg-dark ml-3 mb-3\" style=\"width: 18rem; height: fit-content;\">
                        <h4 class=\"card-header\" style=\"height: 3rem;\">%s</h4>
                        <div class=\"card-body\">
                            <img class=\"card-img\" src=\"%s\" alt=\"%s\">
                            <p class=\"card-text mt-2\">Nota: %s - Ano: %s</p>
                        </div>
                    </div>
                    """;

            body.append(String.format(divTemplate, movie.title(), movie.imageUrl(), movie.title(), movie.rating(),
                    movie.year()));
        }
        body.append("</div> </body>");

        html.append(head);
        html.append(body);
        html.append("</html>");

        writer.write(html.toString());
        writer.flush();

    }
}
