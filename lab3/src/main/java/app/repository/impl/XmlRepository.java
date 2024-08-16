package app.repository.impl;

import app.service.Crypt;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.util.List;
import java.util.Objects;

public abstract class XmlRepository<T, ID> extends ListRepository<T, ID> implements Flushable {
    private static final String CHARSET_NAME = Crypt.CHARSET.name();
    private final File location;

    public XmlRepository(File location) {
        this.location = Objects.requireNonNull(location);
        try {
            XMLInputFactory f = XMLInputFactory.newFactory();
            XMLStreamReader reader = f.createXMLStreamReader(new FileInputStream(this.location), CHARSET_NAME);

            try {
                reader.next();
                reader.next();

                XmlMapper mapper = newMapper();
                init(mapper, reader);

                String className = mapper.readValue(reader, String.class);
                Class<?> tClass = Class.forName(className);

                while (reader.hasNext()) {
                    @SuppressWarnings("unchecked")
                    T o = (T) mapper.readValue(reader, tClass);
                    data.add(o);
                }
            } finally {
                reader.close();
            }
        } catch (Exception ignored) {
        }
    }

    @Override
    public T save(T t) {
        T save = super.save(t);
        flush();
        return save;
    }

    @Override
    public List<T> saveAll(Iterable<T> ts) {
        List<T> list = super.saveAll(ts);
        flush();
        return list;
    }

    @Override
    public void delete(T t) {
        super.delete(t);
        flush();
    }

    @Override
    public void deleteAll() {
        super.deleteAll();
        flush();
    }

    @Override
    public void deleteAll(Iterable<T> ts) {
        super.deleteAll(ts);
        flush();
    }

    @Override
    public void flush() {
        try {
            XMLOutputFactory f = XMLOutputFactory.newFactory();
            XMLStreamWriter writer = f.createXMLStreamWriter(new FileOutputStream(location), CHARSET_NAME);

            writer.writeStartDocument();
            writer.writeStartElement("Data");

            XmlMapper mapper = newMapper();
            write(mapper, writer);
            if (!data.isEmpty()) {
                mapper.writeValue(writer, data.get(0).getClass().getName());

                for (T t : data)
                    mapper.writeValue(writer, t);
            }

            writer.writeEndElement();
            writer.writeEndDocument();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void init(XmlMapper mapper, XMLStreamReader reader) throws IOException {}
    protected void write(XmlMapper mapper, XMLStreamWriter writer) throws IOException {}

    protected XmlMapper newMapper() {
        return new XmlMapper();
    }

    protected abstract ID getId(T t);
}
