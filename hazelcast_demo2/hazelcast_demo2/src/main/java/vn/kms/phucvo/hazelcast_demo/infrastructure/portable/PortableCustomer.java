package vn.kms.phucvo.hazelcast_demo.infrastructure.portable;

import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortableCustomer implements Serializable, Portable {
    String name;
    int age;
    boolean active;


    @Override
    public int getFactoryId() {
        return 1;
    }

    @Override
    public int getClassId() {
        return 1;
    }

    @Override
    public void writePortable(PortableWriter portableWriter) throws IOException {
        portableWriter.writeString("name", name);
        portableWriter.writeInt("age", age);
        portableWriter.writeBoolean("active", active);
    }

    @Override
    public void readPortable(PortableReader portableReader) throws IOException {
        this.name = portableReader.readString("name");
        this.age = portableReader.readInt("age");
        this.active = portableReader.readBoolean("active");
    }
}