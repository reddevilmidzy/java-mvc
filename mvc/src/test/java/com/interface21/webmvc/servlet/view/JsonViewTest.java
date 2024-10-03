package com.interface21.webmvc.servlet.view;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.interface21.web.http.MediaType;

class JsonViewTest {

    @Test
    @DisplayName("JSON으로 응답할 때 ContentType을 MediaType.APPLICATION_JSON_UTF8_VALUE로 반환한다.")
    void return_json_utf8() throws IOException {
        //given
        final Map<String, String> model = Map.of("account", "redddy");
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        final PrintWriter print = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(print);

        //when
        final JsonView jsonView = new JsonView();
        jsonView.render(model, request, response);

        //then
        verify(response).setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    }

    @Test
    @DisplayName("model에 데이터가 1개면 값을 그대로 반환한다")
    void one_data() throws IOException {
        //given
        final Map<String, String> model = Map.of("account", "redddy");
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        final PrintWriter print = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(print);

        //when
        final JsonView jsonView = new JsonView();
        jsonView.render(model, request, response);

        //then
        verify(response.getWriter()).write("\"redddy\"");
    }

    @Test
    @DisplayName("model에 데이터가 2개면 Map 형태로 반환한다.")
    void two_data() throws IOException {
        //given
        final Map<String, String> model = new LinkedHashMap<>();
        model.put("account", "redddy");
        model.put("password", "486");
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        final PrintWriter print = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(print);

        //when
        final JsonView jsonView = new JsonView();
        jsonView.render(model, request, response);

        //then
        verify(response.getWriter()).write("{\"account\":\"redddy\",\"password\":\"486\"}");
    }
}
