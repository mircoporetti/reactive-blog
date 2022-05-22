package me.mircoporetti.reactiveblog.rest.post;

import org.testcontainers.containers.GenericContainer;

class MongoDBContainer extends GenericContainer<MongoDBContainer> {

    private static final int PORT = 27017;

    public MongoDBContainer(final String image) {
        super(image);
        this.addExposedPort(PORT);
    }

    public String getUri() {
        final String ip = this.getHost();
        final Integer port = this.getMappedPort(PORT);
        return String.format("mongodb://%s:%s/test", ip, port);
    }

    @Override
    public void stop() {
        // let the JVM handle the shutdown
    }
}