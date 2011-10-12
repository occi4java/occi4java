package de.irf.it.tuocci.httpng.mime.text;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URL;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: smalpapa
 * Date: 29.06.11
 * Time: 14:55
 * To change this template use File | Settings | File Templates.
 */
@Provider
@Produces("text/uri-list")
public class UriListProducer
        implements MessageBodyWriter<List<URI>> {

    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        boolean isWritable = false;
        if (List.class.isAssignableFrom(type)
                && genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Type[] actualTypeArgs = (parameterizedType.getActualTypeArguments());

            isWritable = (actualTypeArgs.length == 1 && actualTypeArgs[0]
                    .equals(URI.class));
        }
        return isWritable;
    }

    public long getSize(List<URI> urls, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    public void writeTo(List<URI> urls, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(entityStream));
        for(URI u : urls) {
            bw.append(u.toASCIIString()).append('\n');
        }
        bw.flush();
    }
}
