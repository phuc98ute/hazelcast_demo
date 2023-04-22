package vn.kms.phucvo.hazelcast_demo.infrastructure.portable;

import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableFactory;

public class CustomerPortableFactory implements PortableFactory {
    @Override
    public Portable create(int classId ) {
        if ( classId == 1 )
            return new PortableCustomer();
        else
            return null;
    }
}
